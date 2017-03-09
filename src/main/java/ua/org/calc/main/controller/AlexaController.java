package ua.org.calc.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.*;
import java.util.*;
import javax.comm.*;

@Controller
@RequestMapping("/alexa")
public class AlexaController {
    static Enumeration portList;
    static CommPortIdentifier portId;
    static String messageString = "Hello, world!\n";
    static SerialPort serialPort;
    static OutputStream outputStream;

    @RequestMapping("/")
    public String index(@RequestParam(required = false) String result) {
        portList = CommPortIdentifier.getPortIdentifiers();

        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                if (portId.getName().equals("COM3")) {
                    //if (portId.getName().equals("/dev/term/a")) {
                    try {
                        serialPort = (SerialPort)
                                portId.open("SimpleWriteApp", 2000);
                    } catch (PortInUseException e) {
                    }
                    try {
                        outputStream = serialPort.getOutputStream();
                    } catch (IOException e) {
                    }
                    try {
                        serialPort.setSerialPortParams(9600,
                                SerialPort.DATABITS_8,
                                SerialPort.STOPBITS_1,
                                SerialPort.PARITY_NONE);
                    } catch (UnsupportedCommOperationException e) {
                    }
                    try {
                        outputStream.write(messageString.getBytes());
                    } catch (IOException e) {
                    }
                }
            }
        }
        return "index";
    }
}
