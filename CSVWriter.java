import java.io.*;


public class CSVWriter {
    public static void write(String outputCsvfile, String searchValue, String newValue) {

        try {
            // Read the CSV file
            BufferedReader br = new BufferedReader(new FileReader(outputCsvfile));
            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",", -1); // Assuming CSV is comma-separated and -1 to include empty columns
                if (values.length > 0 && values[0].equals(searchValue)) {
                    // Update the corresponding cell in the second column
                    if (values.length > 1) {
                        values[1] = newValue;//update column 2 corresponding matched column
                        line = String.join(",", values);
                    }
                }
                sb.append(line).append("\n");
            }
            br.close();

            // Write the modified data back to the CSV file
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputCsvfile));
            bw.write(sb.toString());
            bw.close();

            System.out.println("Value updated successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }

    }
}
