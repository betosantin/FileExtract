package run;


import fileextract.FileRules;
import fileextract.FileUtilities;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 *
 * @author Roberto Santin
 */
public class Watching {

    public static void main(String[] args) throws IOException {

        FileUtilities fu = new FileUtilities();

        File in = new File(fu.getDataInPath());

        Path folderInput = Files.createDirectories(Paths.get(in.getAbsolutePath()));
        Path folderProcessed = Files.createDirectories(folderInput.resolve("processados"));

        WatchService watch = FileSystems.getDefault().newWatchService();

        // Obtem eventos só para arquivos criados
        folderInput.register(watch, ENTRY_CREATE);

        while (true) 
        {

            WatchKey wk = null;
            try {
                System.out.println("Aguardando novo arquivo na pasta de input " + folderInput);
                wk = watch.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (WatchEvent<?> event : wk.pollEvents()) {

                if (event.kind() == OVERFLOW) {
                    continue;
                }

                //Obtem arquivo criado através do evento disparado
                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path fileName = ev.context();
                Path file = folderInput.resolve(fileName);
                System.out.println("Arquivo encontrado: " + file);

                FileRules flayout = new FileRules();
                flayout.readFile(file.toFile());

                String outputName = file.toFile().getName().replace(".", ".done.");

                System.out.println("Criando Arquivo : " + outputName + " no diretório: " + fu.getDataOutPath());
                PrintWriter writer = new PrintWriter(fu.getDataOutPath() + outputName, "UTF-8");
                writer.println("Amount of clients in the input file: " + flayout.getCustomerSize());
                writer.println("Amount of salesman in the input file: " + flayout.getSalesmanSize());
                writer.println("ID of the most expensive sale: " + flayout.getMostExpensiveSale());
                writer.println("Worst salesman ever: " + flayout.getWorstSalesman());
                writer.close();


                // Move arquivo utilizado para a pasta processados
                Path fileProcessed = folderProcessed.resolve(fileName);
                System.out.println("Movendo arquivo para: " + fileProcessed);
                Files.move(file, fileProcessed, StandardCopyOption.REPLACE_EXISTING);
            }

            if (!wk.reset()) {
                break;
            }
        }

    }

}
