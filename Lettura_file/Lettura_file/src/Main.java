import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        File f = new File("prova.txt");
        File out = new File("scontrino.html");

        if(f.exists()){
            System.out.println("Il file " + f.getName() + " esiste");
            try {
                int i = 0;
                BufferedReader br = new BufferedReader(new FileReader(f));
                BufferedWriter bw = new BufferedWriter(new FileWriter(out));
                ArrayList<String> s = new ArrayList<String>();
                do {
                    s.add(br.readLine());
                    System.out.println(s.get(i));
                    i++;
                } while (s.get(i - 1) != null);
                br.close();

                s.remove(s.size() - 1);
                s.remove(0);

                String[][] sc = new String[s.size()][4];

                for (i = 0; i < s.size(); i++) {
                    sc[i] = s.get(i).split(" ");
                    if (sc[i].length==4 &&  sc[i][3] != null) {
                        sc[i][3].replace("%", "");
                        sc[i][3] = "0." + sc[i][3];
                    }
                }



                double[][] valori = new double[s.size()][3];
                for (i = 0; i < s.size(); i++) {
                    for (int j = 0; j < 2; j++) {
                        valori[i][j] = sc[i][j]!=null? Double.parseDouble(sc[i][j + 1]) : 1;
                    }
                }

                double[] prezzi = new double[s.size()];
                double costo = 0;

                for (i = 0; i < s.size(); i++) {
                    prezzi[i] = valori[i][0] * (1-valori[i][2]) * valori[i][1];
                    costo+=prezzi[i];
                    System.out.println(prezzi[i]);
                }

                bw.write("<html>\n");
                bw.write("\t<head>\n");
                bw.write("\t</head>\n");
                bw.write("\t<body>\n");
                bw.write("\t\t<table border='1'>\n");
                bw.write("\t\t\t<tr>\n");
                bw.write("\t\t\t\t<td>A</td>\n");
                bw.write("\t\t\t\t<td>A</td>\n");
                bw.write("\t\t\t\t<td>A</td>\n");
                bw.write("\t\t\t\t<td>A</td>\n");
                bw.write("\t\t\t</tr>\n");
                for(i=0; i< s.size(); i++){
                    bw.write("\t\t\t\t<tr>");
                    for(int j = 0; j<4; j++){
                        if(j==3 && sc[i].length==4){
                            bw.write("<td>"+sc[i][j]+"</td>");
                        } else if(j<3){
                            bw.write("<td>"+sc[i][j]+"</td>");
                        }
                    }
                    bw.write("<td>"+prezzi[i]+"</td>");
                    bw.write("</tr>\n");
                }
                bw.write("\t\t\t</tr>\n");
                bw.write("\t\t</table>\n");
                bw.write("<tr>"+costo+"</tr>");
                bw.write("\t</body>\n");
                bw.write("</html>\n");

                bw.close();


            }catch(Exception e){
                System.out.println(e);
            }
        }
    }
}