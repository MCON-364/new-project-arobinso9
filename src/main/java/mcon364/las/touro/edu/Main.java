package mcon364.las.touro.edu;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        getGreeting("USERNAME");
        getGreeting("NO_SUCH_VAR");
        processValues( List.of(
                List.of(5, 10, 15),     // Processes completely
                List.of(20, 0, 25),     // Finds 0, skips to next list
                List.of(30, 35, 40),    // Processes completely
                List.of(45, 99, 50),    // Finds 99, terminates everything
                List.of(55, 60, 65)     // Never reached
        ));


    }
    public static Optional<String> getUserName(String envVarName){
        return  Optional.ofNullable(System.getenv(envVarName));
    }
    public static String getGreeting(String envVarName){
        var environmentVarName= getUserName(envVarName);
        if(environmentVarName.isPresent()){
            StringBuilder builder =new StringBuilder();
            builder.append("Hello, ");
            builder.append(environmentVarName.get());
            return builder.toString();
        }
        else
            return "I am sorry. There was no user name";
    }
    public static int processValues(List<List<Integer>> data){
        //a list of lists of integers
        // return how many rows have been processed. aka how many lists we processed
        // when theres a 0 in the list, it should continue;
        // When a 0 is found, use continue outerLoop to skip to the next list.
        // When 99 is found, use break outerLoop to exit all processing immediately.


        int counter=0; //will count how many complete rows/lists have been processed

        outerloop:
        for (int row = 0; row<data.size(); row++){ //the outer list
            for(int sub_row=0; sub_row<data.get(row).size();sub_row++){ //looping each row/list in the main outer list
               // This jumps immediately to the next outer row, skipping the line where counter++ lives.
                //so goes straight back to the start of the outer loop. will skip all code after the continue
                if(data.get(row).get(sub_row)==0)
                    continue outerloop;
                //This kills the entire process immediately, also skipping the counter.
                if(data.get(row).get(sub_row)==99)
                    break outerloop;
            }
            counter++;
        }
        return counter;
    }

}
