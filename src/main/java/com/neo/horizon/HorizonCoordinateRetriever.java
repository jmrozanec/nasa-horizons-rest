package com.neo.horizon;

import com.neo.model.Coordinate;
import org.apache.commons.net.telnet.TelnetClient;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Locale;

//Check this example: https://commons.apache.org/proper/commons-net/examples/telnet/TelnetClientExample.java
public class HorizonCoordinateRetriever {
    private TelnetClient telnet;
    private InputStream in;
    private PrintStream out;
    private String prompt = "Horizons>";

    public HorizonCoordinateRetriever(){
        telnet = new TelnetClient();
    }

    public Coordinate coordinates(int bodyid, DateTime time, String referencePlane){
        try {
            telnet.connect("ssd.jpl.nasa.gov", 6775);
            in = telnet.getInputStream();
            out = new PrintStream(telnet.getOutputStream());
            readUntil(prompt);
            prompt = "[A]pproaches, [E]phemeris, [F]tp,[M]ail,[R]edisplay, [S]PK,?,<cr>";
            sendCommand(String.format("%s;", bodyid));
            prompt = "Observe, Elements, Vectors  [o,e,v,?] : ";
            sendCommand("E");
            prompt = "Coordinate center [ <id>,coord,geo  ] : ";
            sendCommand("v");
            prompt = "Confirm selected station    [ y/n ] --> ";
            sendCommand("399");
            prompt = "Reference plane [eclip, frame, body ] : ";
            sendCommand("y");
            prompt = "Starting CT  [>=   1599-Dec-12 00:00] : ";
            sendCommand(referencePlane);
            prompt = "Ending   CT  [<=   2500-Dec-31 00:00] : ";
            sendCommand(format(time));
            prompt = "Output interval [ex: 10m, 1h, 1d, ? ] : ";
            sendCommand(format(time.plusMinutes(1)));
            prompt = "Accept default output [ cr=(y), n, ?] : ";
            sendCommand("10m");
            prompt = "Select... [A]gain, [N]ew-case, [F]tp, [K]ermit, [M]ail, [R]edisplay, ? : ";
            String result = sendCommand("y");
            sendCommand("]");

            disconnect();

            return parseResult(result);
        } catch (Exception e) {
            e.printStackTrace();//TODO log
        }
        return null;
    }

    public String readUntil(String pattern) {
        try {
            char lastChar = pattern.charAt(pattern.length()-1);
            StringBuffer sb = new StringBuffer();
            boolean found = false;
            char ch = (char) in.read();
            while (true) {
                System.out.print(ch);//TODO delete
                sb.append(ch);
                if (ch == lastChar) {
                    if (sb.toString().endsWith(pattern)) {
                        return sb.toString();
                    }
                }
                ch = (char) in.read();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void write(String value) {
        try {
            out.println(value);
            out.flush();
            System.out.println(value);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String sendCommand(String command) {
        try {
            write(command);
            return readUntil(prompt);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void disconnect() {
        try {
            telnet.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Coordinate parseResult(String result){
        String splits = result.split("\\$\\$SOE")[1].split("\\$\\$EOE")[0].split("TDB")[1];
        splits = splits.replaceAll("[^\\p{ASCII}]", " ").replaceAll("\\s{2,}", " ").replaceAll(" +", " ").replaceAll("\\)", "").trim();
        double [] elements = asDouble(splits.split(" "));
        return new Coordinate(
                elements[0], elements[1], elements[2],
                elements[3], elements[4], elements[5],
                elements[6], elements[7], elements[8]
        );
    }

    double[] asDouble(String[]array){
        double[] elements = new double[array.length];
        for(int j=0; j<array.length; j++){
            elements[j]=Double.parseDouble(array[j]);
        }
        return elements;
    }

    String format(DateTime time){
        return DateTimeFormat.forPattern("yyyy-MMM-dd hh:mm:ss.SSSS").withLocale(Locale.US).print(time);
    }
}
