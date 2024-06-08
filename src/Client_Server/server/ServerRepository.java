package Client_Server.server;

import java.io.FileReader;
import java.io.FileWriter;

public class ServerRepository implements IServerRepository{

    public static final String LOG_PATH = "src/Client_Server/log/log.txt";

    public String getLog() {
        return readLog();
    }

    public void saveInLog(String text){
        try (FileWriter writer = new FileWriter(LOG_PATH, true)){
            writer.write(text);
            writer.write("\n");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private String readLog(){
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(LOG_PATH)){
            int c;
            while ((c = reader.read()) != -1){
                stringBuilder.append((char) c);
            }
            if (!stringBuilder.isEmpty()) {
                stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length()); //We delete last symbol - \n
            }
            return stringBuilder.toString();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
