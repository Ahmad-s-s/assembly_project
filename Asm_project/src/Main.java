import Functions.Functions;
import Variables.Flags;
import Variables.Registers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.in;

public class Main {

    static String reverseString(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        return sb.toString();
    }

    static int stateReadReg() {
        /*
        1 for eax, 2 for ebx, 3 for ecx, 4 for edx, 5 for esi, 6 for edi, 7 for esp, 8 for ebp
         */
        String reg = fileScanner.next();
        reg = reg.toLowerCase();
        if (reg.equals("comment")) {
            String commentChar = fileScanner.next();
            while (fileScanner.hasNextLine()) {
                String next = fileScanner.next();
                if (next.equals(commentChar)) {
                    break;
                }
            }
            reg = fileScanner.next();
            reg = reg.toLowerCase();
        }
        reg = reg.replace(",", "");
        switch (reg) {
            case "eax":
                return 1;
            case "ebx":
                return 2;
            case "ecx":
                return 3;
            case "edx":
                return 4;
            case "esi":
                return 5;
            case "edi":
                return 6;
            case "esp":
                return 7;
            case "ebp":
                return 8;

            case "ax":
                return 11;
            case "bx":
                return 21;
            case "cx":
                return 31;
            case "dx":
                return 41;
            case "si":
                return 51;
            case "di":
                return 61;
            case "sp":
                return 71;
            case "bp":
                return 81;

            case "ah":
                return 111;
            case "al":
                return 112;

            case "bh":
                return 211;
            case "bl":
                return 212;

            case "ch":
                return 311;
            case "cl":
                return 312;

            case "dh":
                return 411;
            case "dl":
                return 412;

        }
        System.out.println("Invalid register");
        return -1;

    }

    static int stateReadInstruction() {
        /*// 1 if mov, 2 if add, 3 if sub, 11 if inc, 12 if dec
        // 6 if and, 7 if or, 8 if xor, 9 if not, 10 if neg*/
        String instruction = fileScanner.next();
        instruction = instruction.toLowerCase();
        if (instruction.equals("comment")) {
            String commentChar = fileScanner.next();
            while (fileScanner.hasNextLine()) {
                String next = fileScanner.next();
                if (next.equals(commentChar)) {
                    break;
                }
            }
            instruction = fileScanner.next();
            instruction = instruction.toLowerCase();
        }
        instruction = instruction.replace(",", "");
        switch (instruction) {
            case "mov":
                return 1;
            case "add":
                return 2;
            case "sub":
                return 3;

            case "inc":
                return 11;
            case "dec":
                return 12;
            case "and":
                return 6;
            case "or":
                return 7;
            case "xor":
                return 8;


            case "not":
                return 9;
            case "neg":
                return 10;
        }
        System.out.println("Invalid instruction, the app crashed");
        return -1;
    }

    static int stateReadVal() {
        String val = fileScanner.next();
        val = val.toLowerCase();
        if (val.equals("comment")) {
            String commentChar = fileScanner.next();
            while (fileScanner.hasNextLine()) {
                String next = fileScanner.next();
                if (next.equals(commentChar)) {
                    break;
                }
            }
            val = fileScanner.next();
            val = val.toLowerCase();
        }
        switch (val) {
            case "eax":
                return -1;
            case "ebx":
                return -2;
            case "ecx":
                return -3;
            case "edx":
                return -4;
            case "esi":
                return -5;
            case "edi":
                return -6;
            case "esp":
                return -7;
            case "ebp":
                return -8;

            case "ax":
                return -11;
            case "bx":
                return -21;
            case "cx":
                return -31;
            case "dx":
                return -41;
            case "si":
                return -51;
            case "di":
                return -61;
            case "sp":
                return -71;
            case "bp":
                return -81;

            case "ah":
                return -111;
            case "al":
                return -112;

            case "bh":
                return -211;
            case "bl":
                return -212;

            case "ch":
                return -311;
            case "cl":
                return -312;

            case "dh":
                return -411;
            case "dl":
                return -412;
        }
        if (val.charAt(val.length() - 1) == 'b') {
            String recordValue = val.substring(0, val.length() - 1);
            value = reverseString(recordValue);
        } else if (val.charAt(val.length() - 1) == 'h' || val.charAt(val.length() - 1) == 'x') {
            String recordValue = val.substring(0, val.length() - 1);
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < recordValue.length(); i++) {
                switch (recordValue.charAt(i)) {
                    case '1':
                        builder.append("0001");
                        break;
                    case '2':
                        builder.append("0010");
                        break;
                    case '3':
                        builder.append("0011");
                        break;
                    case '4':
                        builder.append("0100");
                        break;
                    case '5':
                        builder.append("0101");
                        break;
                    case '6':
                        builder.append("0110");
                        break;
                    case '7':
                        builder.append("0111");
                        break;
                    case '8':
                        builder.append("1000");
                        break;
                    case '9':
                        builder.append("1001");
                        break;
                    case 'a':
                        builder.append("1010");
                        break;
                    case 'b':
                        builder.append("1011");
                        break;
                    case 'c':
                        builder.append("1100");
                        break;
                    case 'd':
                        builder.append("1101");
                        break;
                    case 'e':
                        builder.append("1110");
                        break;
                    case 'f':
                        builder.append("1111");
                        break;
                }
            }
            value = reverseString(builder.toString());
            return 1;
        } else if (val.charAt(val.length() - 1) == 'o' || val.charAt(val.length() - 1) == 'q') {
            String recordValue = val.substring(0, val.length() - 1);
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < recordValue.length(); i++) {
                switch (recordValue.charAt(i)) {
                    case '1':
                        builder.append("001");
                        break;
                    case '2':
                        builder.append("010");
                        break;
                    case '3':
                        builder.append("011");
                        break;
                    case '4':
                        builder.append("100");
                        break;
                    case '5':
                        builder.append("101");
                        break;
                    case '6':
                        builder.append("110");
                        break;
                    case '7':
                        builder.append("111");
                        break;
                }
            }
            value = reverseString(builder.toString());
            return 1;
        } else {
            Integer decimal = Integer.parseInt(val);
            if (decimal > 0) {
                value = Integer.toBinaryString(decimal);
                value = reverseString(value);
            } else {
                decimal *= -1;
                String recordValue = Integer.toBinaryString(decimal);
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < recordValue.length(); i++) {
                    if (recordValue.charAt(i) == '1') {
                        builder.append('0');
                    } else {
                        builder.append('1');
                    }
                }
                value = reverseString(builder.toString());
            }
            return 1;
        }
        System.out.println("not valid");
        return -1000;
    }

    static String value;
    static File myFile;
    static Scanner fileScanner;

    public static void main(String[] args) {
        Registers.setReg();
        myFile = new File("D:\\Uni\\Semester 3\\Assembly_project\\assembly_project\\Asm_project\\src\\asm.txt");
        try {
            fileScanner = new Scanner(myFile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        while (fileScanner.hasNext()) {
            int instruction = stateReadInstruction();
            if (instruction == -1) {
                System.out.println("An error appeared");
                exit(0);
            }
            int reg = stateReadReg();
            if (reg == -1) {
                System.out.println("An error appeared");
                exit(0);
            }
            int source;
            if (instruction >= 9) {
                //...
                continue;
            } else {
                source = stateReadVal();
                if (source == -1000) {
                    System.out.println("An error appeared");
                    exit(0);
                }
            }
            if (source == 1) {
                if (instruction == 1) { //mov
                    if (reg == 1) { //eax
                        Registers.eax = Functions.mov(Registers.eax, value);
                    } else if (reg == 2) { //ebx
                        Registers.ebx = Functions.mov(Registers.ebx, value);
                    } else if (reg == 3) { //ecx
                        Registers.ecx = Functions.mov(Registers.ecx, value);

                    } else if (reg == 4) { //edx
                        Registers.edx = Functions.mov(Registers.edx, value);

                    } else if (reg == 5) { //esi
                        Registers.esi = Functions.mov(Registers.esi, value);

                    } else if (reg == 6) { //edi
                        Registers.edi = Functions.mov(Registers.edi, value);

                    } else if (reg == 7) { //esp
                        Registers.esp = Functions.mov(Registers.esp, value);

                    } else if (reg == 8) { //ebp
                        Registers.ebp = Functions.mov(Registers.ebp, value);
                    } else if (reg == 11) { //ax
                        ArrayList<Integer> ax = new ArrayList<>();
                        for (int i = 16; i < Registers.eax.size(); i++) {
                            ax.add(Registers.eax.get(i));
                        }
                        ax = Functions.mov(ax,value);
                        for (int i = 16; i < Registers.eax.size(); i++) {
                            Registers.eax.set(i, ax.get(i-16));
                        }
                    } else if (reg == 21) { //bx
                        ArrayList<Integer> bx = new ArrayList<>();
                        for (int i = 16; i < Registers.ebx.size(); i++) {
                            bx.add(Registers.ebx.get(i));
                        }
                        bx = Functions.mov(bx,value);
                        for (int i = 16; i < Registers.ebx.size(); i++) {
                            Registers.ebx.set(i, bx.get(i-16));
                        }
                    } else if (reg == 31) { //cx
                        ArrayList<Integer> cx = new ArrayList<>();
                        for (int i = 16; i < Registers.ecx.size(); i++) {
                            cx.add(Registers.ecx.get(i));
                        }
                        cx = Functions.mov(cx,value);
                        for (int i = 16; i < Registers.ecx.size(); i++) {
                            Registers.ecx.set(i, cx.get(i-16));
                        }
                    } else if (reg == 41) { //dx
                        ArrayList<Integer> dx = new ArrayList<>();
                        for (int i = 16; i < Registers.edx.size(); i++) {
                            dx.add(Registers.edx.get(i));
                        }
                        dx = Functions.mov(dx,value);
                        for (int i = 16; i < Registers.edx.size(); i++) {
                            Registers.edx.set(i, dx.get(i-16));
                        }
                    } else if (reg == 51) { //si
                        ArrayList<Integer> si = new ArrayList<>();
                        for (int i = 16; i < Registers.esi.size(); i++) {
                            si.add(Registers.esi.get(i));
                        }
                        si = Functions.mov(si,value);
                        for (int i = 16; i < Registers.esi.size(); i++) {
                            Registers.esi.set(i, si.get(i-16));
                        }
                    } else if (reg == 61) { //di
                        ArrayList<Integer> di = new ArrayList<>();
                        for (int i = 16; i < Registers.edi.size(); i++) {
                            di.add(Registers.edx.get(i));
                        }
                        di = Functions.mov(di,value);
                        for (int i = 16; i < Registers.edi.size(); i++) {
                            Registers.edi.set(i, di.get(i-16));
                        }
                    } else if (reg == 71) { //sp
                        ArrayList<Integer> sp = new ArrayList<>();
                        for (int i = 16; i < Registers.esp.size(); i++) {
                            sp.add(Registers.esp.get(i));
                        }
                        sp = Functions.mov(sp,value);
                        for (int i = 16; i < Registers.esp.size(); i++) {
                            Registers.esp.set(i, sp.get(i-16));
                        }
                    } else if (reg == 81) { //bp
                        ArrayList<Integer> bp = new ArrayList<>();
                        for (int i = 16; i < Registers.ebp.size(); i++) {
                            bp.add(Registers.ebp.get(i));
                        }
                        bp = Functions.mov(bp,value);
                        for (int i = 16; i < Registers.ebp.size(); i++) {
                            Registers.ebp.set(i, bp.get(i-16));
                        }
                    } else if (reg == 111) { //ah
                        ArrayList<Integer> ah = new ArrayList<>();
                        for (int i = 16; i < Registers.eax.size()-8; i++) {
                            ah.add(Registers.eax.get(i));
                        }
                        ah = Functions.mov(ah,value);
                        for (int i = 16; i < Registers.eax.size()-8; i++) {
                            Registers.eax.set(i, ah.get(i-16));
                        }
                    } else if (reg == 112) { //al
                        ArrayList<Integer> al = new ArrayList<>();
                        for (int i = 24; i < Registers.eax.size(); i++) {
                            al.add(Registers.eax.get(i));
                        }
                        al = Functions.mov(al,value);
                        for (int i = 24; i < Registers.eax.size(); i++) {
                            Registers.eax.set(i, al.get(i-24));
                        }
                    } else if (reg == 211) { //bh
                        ArrayList<Integer> bh = new ArrayList<>();
                        for (int i = 16; i < Registers.ebx.size()-8; i++) {
                            bh.add(Registers.ebx.get(i));
                        }
                        bh = Functions.mov(bh,value);
                        for (int i = 16; i < Registers.ebx.size()-8; i++) {
                            Registers.ebx.set(i, bh.get(i-16));
                        }
                    } else if (reg == 212) { //bl
                        ArrayList<Integer> bl = new ArrayList<>();
                        for (int i = 24; i < Registers.ebx.size(); i++) {
                            bl.add(Registers.ebx.get(i));
                        }
                        bl = Functions.mov(bl,value);
                        for (int i = 24; i < Registers.ebx.size(); i++) {
                            Registers.ebx.set(i, bl.get(i-24));
                        }
                    } else if (reg == 311) { //ch
                        ArrayList<Integer> ch = new ArrayList<>();
                        for (int i = 16; i < Registers.ecx.size()-8; i++) {
                            ch.add(Registers.ecx.get(i));
                        }
                        ch = Functions.mov(ch,value);
                        for (int i = 16; i < Registers.ecx.size()-8; i++) {
                            Registers.ecx.set(i, ch.get(i-16));
                        }
                    } else if (reg == 312) { //cl
                        ArrayList<Integer> cl = new ArrayList<>();
                        for (int i = 24; i < Registers.ecx.size(); i++) {
                            cl.add(Registers.ecx.get(i));
                        }
                        cl = Functions.mov(cl,value);
                        for (int i = 24; i < Registers.ecx.size(); i++) {
                            Registers.ecx.set(i, cl.get(i-24));
                        }
                    } else if (reg == 411) { //dh
                        ArrayList<Integer> dh = new ArrayList<>();
                        for (int i = 16; i < Registers.edx.size()-8; i++) {
                            dh.add(Registers.ebx.get(i));
                        }
                        dh = Functions.mov(dh,value);
                        for (int i = 16; i < Registers.edx.size()-8; i++) {
                            Registers.edx.set(i, dh.get(i-16));
                        }
                    } else if (reg == 412) { //dl
                        ArrayList<Integer> dl = new ArrayList<>();
                        for (int i = 24; i < Registers.edx.size(); i++) {
                            dl.add(Registers.ebx.get(i));
                        }
                        dl = Functions.mov(dl,value);
                        for (int i = 24; i < Registers.edx.size(); i++) {
                            Registers.edx.set(i, dl.get(i-24));
                        }
                    }
                } else if (instruction == 2) { //add
                    if (reg == 1) { //eax
                        Registers.eax = Functions.add(Registers.eax, value);
                    } else if (reg == 2) { //ebx
                        Registers.ebx = Functions.add(Registers.ebx, value);
                    } else if (reg == 3) { //ecx
                        Registers.ecx = Functions.add(Registers.ecx, value);

                    } else if (reg == 4) { //edx
                        Registers.edx = Functions.add(Registers.edx, value);

                    } else if (reg == 5) { //esi
                        Registers.esi = Functions.add(Registers.esi, value);

                    } else if (reg == 6) { //edi
                        Registers.edi = Functions.add(Registers.edi, value);

                    } else if (reg == 7) { //esp
                        Registers.esp = Functions.add(Registers.esp, value);

                    } else if (reg == 8) { //ebp
                        Registers.ebp = Functions.add(Registers.ebp, value);
                    } else if (reg == 11) { //ax
                        ArrayList<Integer> ax = new ArrayList<>();
                        for (int i = 16; i < Registers.eax.size(); i++) {
                            ax.add(Registers.eax.get(i));
                        }
                        ax = Functions.add(ax,value);
                        for (int i = 16; i < Registers.eax.size(); i++) {
                            Registers.eax.set(i, ax.get(i-16));
                        }
                    } else if (reg == 21) { //bx
                        ArrayList<Integer> bx = new ArrayList<>();
                        for (int i = 16; i < Registers.ebx.size(); i++) {
                            bx.add(Registers.ebx.get(i));
                        }
                        bx = Functions.add(bx,value);
                        for (int i = 16; i < Registers.ebx.size(); i++) {
                            Registers.ebx.set(i, bx.get(i-16));
                        }
                    } else if (reg == 31) { //cx
                        ArrayList<Integer> cx = new ArrayList<>();
                        for (int i = 16; i < Registers.ecx.size(); i++) {
                            cx.add(Registers.ecx.get(i));
                        }
                        cx = Functions.add(cx,value);
                        for (int i = 16; i < Registers.ecx.size(); i++) {
                            Registers.ecx.set(i, cx.get(i-16));
                        }
                    } else if (reg == 41) { //dx
                        ArrayList<Integer> dx = new ArrayList<>();
                        for (int i = 16; i < Registers.edx.size(); i++) {
                            dx.add(Registers.edx.get(i));
                        }
                        dx = Functions.add(dx,value);
                        for (int i = 16; i < Registers.edx.size(); i++) {
                            Registers.edx.set(i, dx.get(i-16));
                        }
                    } else if (reg == 51) { //si
                        ArrayList<Integer> si = new ArrayList<>();
                        for (int i = 16; i < Registers.esi.size(); i++) {
                            si.add(Registers.esi.get(i));
                        }
                        si = Functions.add(si,value);
                        for (int i = 16; i < Registers.esi.size(); i++) {
                            Registers.esi.set(i, si.get(i-16));
                        }
                    } else if (reg == 61) { //di
                        ArrayList<Integer> di = new ArrayList<>();
                        for (int i = 16; i < Registers.edi.size(); i++) {
                            di.add(Registers.edx.get(i));
                        }
                        di = Functions.add(di,value);
                        for (int i = 16; i < Registers.edi.size(); i++) {
                            Registers.edi.set(i, di.get(i-16));
                        }
                    } else if (reg == 71) { //sp
                        ArrayList<Integer> sp = new ArrayList<>();
                        for (int i = 16; i < Registers.esp.size(); i++) {
                            sp.add(Registers.esp.get(i));
                        }
                        sp = Functions.add(sp,value);
                        for (int i = 16; i < Registers.esp.size(); i++) {
                            Registers.esp.set(i, sp.get(i-16));
                        }
                    } else if (reg == 81) { //bp
                        ArrayList<Integer> bp = new ArrayList<>();
                        for (int i = 16; i < Registers.ebp.size(); i++) {
                            bp.add(Registers.ebp.get(i));
                        }
                        bp = Functions.add(bp,value);
                        for (int i = 16; i < Registers.ebp.size(); i++) {
                            Registers.ebp.set(i, bp.get(i-16));
                        }
                    } else if (reg == 111) { //ah
                        ArrayList<Integer> ah = new ArrayList<>();
                        for (int i = 16; i < Registers.eax.size()-8; i++) {
                            ah.add(Registers.eax.get(i));
                        }
                        ah = Functions.add(ah,value);
                        for (int i = 16; i < Registers.eax.size()-8; i++) {
                            Registers.eax.set(i, ah.get(i-16));
                        }
                    } else if (reg == 112) { //al
                        ArrayList<Integer> al = new ArrayList<>();
                        for (int i = 24; i < Registers.eax.size(); i++) {
                            al.add(Registers.eax.get(i));
                        }
                        al = Functions.add(al,value);
                        for (int i = 24; i < Registers.eax.size(); i++) {
                            Registers.eax.set(i, al.get(i-24));
                        }
                    } else if (reg == 211) { //bh
                        ArrayList<Integer> bh = new ArrayList<>();
                        for (int i = 16; i < Registers.ebx.size()-8; i++) {
                            bh.add(Registers.ebx.get(i));
                        }
                        bh = Functions.add(bh,value);
                        for (int i = 16; i < Registers.ebx.size()-8; i++) {
                            Registers.ebx.set(i, bh.get(i-16));
                        }
                    } else if (reg == 212) { //bl
                        ArrayList<Integer> bl = new ArrayList<>();
                        for (int i = 24; i < Registers.ebx.size(); i++) {
                            bl.add(Registers.ebx.get(i));
                        }
                        bl = Functions.add(bl,value);
                        for (int i = 24; i < Registers.ebx.size(); i++) {
                            Registers.ebx.set(i, bl.get(i-24));
                        }
                    } else if (reg == 311) { //ch
                        ArrayList<Integer> ch = new ArrayList<>();
                        for (int i = 16; i < Registers.ecx.size()-8; i++) {
                            ch.add(Registers.ecx.get(i));
                        }
                        ch = Functions.add(ch,value);
                        for (int i = 16; i < Registers.ecx.size()-8; i++) {
                            Registers.ecx.set(i, ch.get(i-16));
                        }
                    } else if (reg == 312) { //cl
                        ArrayList<Integer> cl = new ArrayList<>();
                        for (int i = 24; i < Registers.ecx.size(); i++) {
                            cl.add(Registers.ecx.get(i));
                        }
                        cl = Functions.add(cl,value);
                        for (int i = 24; i < Registers.ecx.size(); i++) {
                            Registers.ecx.set(i, cl.get(i-24));
                        }
                    } else if (reg == 411) { //dh
                        ArrayList<Integer> dh = new ArrayList<>();
                        for (int i = 16; i < Registers.edx.size()-8; i++) {
                            dh.add(Registers.ebx.get(i));
                        }
                        dh = Functions.add(dh,value);
                        for (int i = 16; i < Registers.edx.size()-8; i++) {
                            Registers.edx.set(i, dh.get(i-16));
                        }
                    } else if (reg == 412) { //dl
                        ArrayList<Integer> dl = new ArrayList<>();
                        for (int i = 24; i < Registers.edx.size(); i++) {
                            dl.add(Registers.ebx.get(i));
                        }
                        dl = Functions.add(dl,value);
                        for (int i = 24; i < Registers.edx.size(); i++) {
                            Registers.edx.set(i, dl.get(i-24));
                        }
                    }

                } else if (instruction == 3) { //sub
                    if (reg == 1) { //eax
                        Registers.eax = Functions.sub(Registers.eax, value);
                    } else if (reg == 2) { //ebx
                        Registers.ebx = Functions.sub(Registers.ebx, value);
                    } else if (reg == 3) { //ecx
                        Registers.ecx = Functions.sub(Registers.ecx, value);

                    } else if (reg == 4) { //edx
                        Registers.edx = Functions.sub(Registers.edx, value);

                    } else if (reg == 5) { //esi
                        Registers.esi = Functions.sub(Registers.esi, value);

                    } else if (reg == 6) { //edi
                        Registers.edi = Functions.sub(Registers.edi, value);

                    } else if (reg == 7) { //esp
                        Registers.esp = Functions.sub(Registers.esp, value);

                    } else if (reg == 8) { //ebp
                        Registers.ebp = Functions.sub(Registers.ebp, value);
                    } else if (reg == 11) { //ax
                        ArrayList<Integer> ax = new ArrayList<>();
                        for (int i = 16; i < Registers.eax.size(); i++) {
                            ax.add(Registers.eax.get(i));
                        }
                        ax = Functions.sub(ax,value);
                        for (int i = 16; i < Registers.eax.size(); i++) {
                            Registers.eax.set(i, ax.get(i-16));
                        }
                    } else if (reg == 21) { //bx
                        ArrayList<Integer> bx = new ArrayList<>();
                        for (int i = 16; i < Registers.ebx.size(); i++) {
                            bx.add(Registers.ebx.get(i));
                        }
                        bx = Functions.sub(bx,value);
                        for (int i = 16; i < Registers.ebx.size(); i++) {
                            Registers.ebx.set(i, bx.get(i-16));
                        }
                    } else if (reg == 31) { //cx
                        ArrayList<Integer> cx = new ArrayList<>();
                        for (int i = 16; i < Registers.ecx.size(); i++) {
                            cx.add(Registers.ecx.get(i));
                        }
                        cx = Functions.sub(cx,value);
                        for (int i = 16; i < Registers.ecx.size(); i++) {
                            Registers.ecx.set(i, cx.get(i-16));
                        }
                    } else if (reg == 41) { //dx
                        ArrayList<Integer> dx = new ArrayList<>();
                        for (int i = 16; i < Registers.edx.size(); i++) {
                            dx.add(Registers.edx.get(i));
                        }
                        dx = Functions.sub(dx,value);
                        for (int i = 16; i < Registers.edx.size(); i++) {
                            Registers.edx.set(i, dx.get(i-16));
                        }
                    } else if (reg == 51) { //si
                        ArrayList<Integer> si = new ArrayList<>();
                        for (int i = 16; i < Registers.esi.size(); i++) {
                            si.add(Registers.esi.get(i));
                        }
                        si = Functions.sub(si,value);
                        for (int i = 16; i < Registers.esi.size(); i++) {
                            Registers.esi.set(i, si.get(i-16));
                        }
                    } else if (reg == 61) { //di
                        ArrayList<Integer> di = new ArrayList<>();
                        for (int i = 16; i < Registers.edi.size(); i++) {
                            di.add(Registers.edx.get(i));
                        }
                        di = Functions.sub(di,value);
                        for (int i = 16; i < Registers.edi.size(); i++) {
                            Registers.edi.set(i, di.get(i-16));
                        }
                    } else if (reg == 71) { //sp
                        ArrayList<Integer> sp = new ArrayList<>();
                        for (int i = 16; i < Registers.esp.size(); i++) {
                            sp.add(Registers.esp.get(i));
                        }
                        sp = Functions.sub(sp,value);
                        for (int i = 16; i < Registers.esp.size(); i++) {
                            Registers.esp.set(i, sp.get(i-16));
                        }
                    } else if (reg == 81) { //bp
                        ArrayList<Integer> bp = new ArrayList<>();
                        for (int i = 16; i < Registers.ebp.size(); i++) {
                            bp.add(Registers.ebp.get(i));
                        }
                        bp = Functions.sub(bp,value);
                        for (int i = 16; i < Registers.ebp.size(); i++) {
                            Registers.ebp.set(i, bp.get(i-16));
                        }
                    } else if (reg == 111) { //ah
                        ArrayList<Integer> ah = new ArrayList<>();
                        for (int i = 16; i < Registers.eax.size()-8; i++) {
                            ah.add(Registers.eax.get(i));
                        }
                        ah = Functions.sub(ah,value);
                        for (int i = 16; i < Registers.eax.size()-8; i++) {
                            Registers.eax.set(i, ah.get(i-16));
                        }
                    } else if (reg == 112) { //al
                        ArrayList<Integer> al = new ArrayList<>();
                        for (int i = 24; i < Registers.eax.size(); i++) {
                            al.add(Registers.eax.get(i));
                        }
                        al = Functions.sub(al,value);
                        for (int i = 24; i < Registers.eax.size(); i++) {
                            Registers.eax.set(i, al.get(i-24));
                        }
                    } else if (reg == 211) { //bh
                        ArrayList<Integer> bh = new ArrayList<>();
                        for (int i = 16; i < Registers.ebx.size()-8; i++) {
                            bh.add(Registers.ebx.get(i));
                        }
                        bh = Functions.sub(bh,value);
                        for (int i = 16; i < Registers.ebx.size()-8; i++) {
                            Registers.ebx.set(i, bh.get(i-16));
                        }
                    } else if (reg == 212) { //bl
                        ArrayList<Integer> bl = new ArrayList<>();
                        for (int i = 24; i < Registers.ebx.size(); i++) {
                            bl.add(Registers.ebx.get(i));
                        }
                        bl = Functions.sub(bl,value);
                        for (int i = 24; i < Registers.ebx.size(); i++) {
                            Registers.ebx.set(i, bl.get(i-24));
                        }
                    } else if (reg == 311) { //ch
                        ArrayList<Integer> ch = new ArrayList<>();
                        for (int i = 16; i < Registers.ecx.size()-8; i++) {
                            ch.add(Registers.ecx.get(i));
                        }
                        ch = Functions.sub(ch,value);
                        for (int i = 16; i < Registers.ecx.size()-8; i++) {
                            Registers.ecx.set(i, ch.get(i-16));
                        }
                    } else if (reg == 312) { //cl
                        ArrayList<Integer> cl = new ArrayList<>();
                        for (int i = 24; i < Registers.ecx.size(); i++) {
                            cl.add(Registers.ecx.get(i));
                        }
                        cl = Functions.sub(cl,value);
                        for (int i = 24; i < Registers.ecx.size(); i++) {
                            Registers.ecx.set(i, cl.get(i-24));
                        }
                    } else if (reg == 411) { //dh
                        ArrayList<Integer> dh = new ArrayList<>();
                        for (int i = 16; i < Registers.edx.size()-8; i++) {
                            dh.add(Registers.ebx.get(i));
                        }
                        dh = Functions.sub(dh,value);
                        for (int i = 16; i < Registers.edx.size()-8; i++) {
                            Registers.edx.set(i, dh.get(i-16));
                        }
                    } else if (reg == 412) { //dl
                        ArrayList<Integer> dl = new ArrayList<>();
                        for (int i = 24; i < Registers.edx.size(); i++) {
                            dl.add(Registers.ebx.get(i));
                        }
                        dl = Functions.sub(dl,value);
                        for (int i = 24; i < Registers.edx.size(); i++) {
                            Registers.edx.set(i, dl.get(i-24));
                        }
                    }
                } else if (instruction == 6) { //
                    if (reg == 1) { //eax
                        Registers.eax = Functions.and(Registers.eax, value);
                    } else if (reg == 2) { //ebx
                        Registers.ebx = Functions.and(Registers.ebx, value);
                    } else if (reg == 3) { //ecx
                        Registers.ecx = Functions.and(Registers.ecx, value);

                    } else if (reg == 4) { //edx
                        Registers.edx = Functions.and(Registers.edx, value);

                    } else if (reg == 5) { //esi
                        Registers.esi = Functions.and(Registers.esi, value);

                    } else if (reg == 6) { //edi
                        Registers.edi = Functions.and(Registers.edi, value);

                    } else if (reg == 7) { //esp
                        Registers.esp = Functions.and(Registers.esp, value);

                    } else if (reg == 8) { //ebp
                        Registers.ebp = Functions.and(Registers.ebp, value);
                    } else if (reg == 11) { //ax
                        ArrayList<Integer> ax = new ArrayList<>();
                        for (int i = 16; i < Registers.eax.size(); i++) {
                            ax.add(Registers.eax.get(i));
                        }
                        ax = Functions.and(ax,value);
                        for (int i = 16; i < Registers.eax.size(); i++) {
                            Registers.eax.set(i, ax.get(i-16));
                        }
                    } else if (reg == 21) { //bx
                        ArrayList<Integer> bx = new ArrayList<>();
                        for (int i = 16; i < Registers.ebx.size(); i++) {
                            bx.add(Registers.ebx.get(i));
                        }
                        bx = Functions.and(bx,value);
                        for (int i = 16; i < Registers.ebx.size(); i++) {
                            Registers.ebx.set(i, bx.get(i-16));
                        }
                    } else if (reg == 31) { //cx
                        ArrayList<Integer> cx = new ArrayList<>();
                        for (int i = 16; i < Registers.ecx.size(); i++) {
                            cx.add(Registers.ecx.get(i));
                        }
                        cx = Functions.and(cx,value);
                        for (int i = 16; i < Registers.ecx.size(); i++) {
                            Registers.ecx.set(i, cx.get(i-16));
                        }
                    } else if (reg == 41) { //dx
                        ArrayList<Integer> dx = new ArrayList<>();
                        for (int i = 16; i < Registers.edx.size(); i++) {
                            dx.add(Registers.edx.get(i));
                        }
                        dx = Functions.and(dx,value);
                        for (int i = 16; i < Registers.edx.size(); i++) {
                            Registers.edx.set(i, dx.get(i-16));
                        }
                    } else if (reg == 51) { //si
                        ArrayList<Integer> si = new ArrayList<>();
                        for (int i = 16; i < Registers.esi.size(); i++) {
                            si.add(Registers.esi.get(i));
                        }
                        si = Functions.and(si,value);
                        for (int i = 16; i < Registers.esi.size(); i++) {
                            Registers.esi.set(i, si.get(i-16));
                        }
                    } else if (reg == 61) { //di
                        ArrayList<Integer> di = new ArrayList<>();
                        for (int i = 16; i < Registers.edi.size(); i++) {
                            di.add(Registers.edx.get(i));
                        }
                        di = Functions.and(di,value);
                        for (int i = 16; i < Registers.edi.size(); i++) {
                            Registers.edi.set(i, di.get(i-16));
                        }
                    } else if (reg == 71) { //sp
                        ArrayList<Integer> sp = new ArrayList<>();
                        for (int i = 16; i < Registers.esp.size(); i++) {
                            sp.add(Registers.esp.get(i));
                        }
                        sp = Functions.and(sp,value);
                        for (int i = 16; i < Registers.esp.size(); i++) {
                            Registers.esp.set(i, sp.get(i-16));
                        }
                    } else if (reg == 81) { //bp
                        ArrayList<Integer> bp = new ArrayList<>();
                        for (int i = 16; i < Registers.ebp.size(); i++) {
                            bp.add(Registers.ebp.get(i));
                        }
                        bp = Functions.and(bp,value);
                        for (int i = 16; i < Registers.ebp.size(); i++) {
                            Registers.ebp.set(i, bp.get(i-16));
                        }
                    } else if (reg == 111) { //ah
                        ArrayList<Integer> ah = new ArrayList<>();
                        for (int i = 16; i < Registers.eax.size()-8; i++) {
                            ah.add(Registers.eax.get(i));
                        }
                        ah = Functions.and(ah,value);
                        for (int i = 16; i < Registers.eax.size()-8; i++) {
                            Registers.eax.set(i, ah.get(i-16));
                        }
                    } else if (reg == 112) { //al
                        ArrayList<Integer> al = new ArrayList<>();
                        for (int i = 24; i < Registers.eax.size(); i++) {
                            al.add(Registers.eax.get(i));
                        }
                        al = Functions.and(al,value);
                        for (int i = 24; i < Registers.eax.size(); i++) {
                            Registers.eax.set(i, al.get(i-24));
                        }
                    } else if (reg == 211) { //bh
                        ArrayList<Integer> bh = new ArrayList<>();
                        for (int i = 16; i < Registers.ebx.size()-8; i++) {
                            bh.add(Registers.ebx.get(i));
                        }
                        bh = Functions.and(bh,value);
                        for (int i = 16; i < Registers.ebx.size()-8; i++) {
                            Registers.ebx.set(i, bh.get(i-16));
                        }
                    } else if (reg == 212) { //bl
                        ArrayList<Integer> bl = new ArrayList<>();
                        for (int i = 24; i < Registers.ebx.size(); i++) {
                            bl.add(Registers.ebx.get(i));
                        }
                        bl = Functions.and(bl,value);
                        for (int i = 24; i < Registers.ebx.size(); i++) {
                            Registers.ebx.set(i, bl.get(i-24));
                        }
                    } else if (reg == 311) { //ch
                        ArrayList<Integer> ch = new ArrayList<>();
                        for (int i = 16; i < Registers.ecx.size()-8; i++) {
                            ch.add(Registers.ecx.get(i));
                        }
                        ch = Functions.and(ch,value);
                        for (int i = 16; i < Registers.ecx.size()-8; i++) {
                            Registers.ecx.set(i, ch.get(i-16));
                        }
                    } else if (reg == 312) { //cl
                        ArrayList<Integer> cl = new ArrayList<>();
                        for (int i = 24; i < Registers.ecx.size(); i++) {
                            cl.add(Registers.ecx.get(i));
                        }
                        cl = Functions.and(cl,value);
                        for (int i = 24; i < Registers.ecx.size(); i++) {
                            Registers.ecx.set(i, cl.get(i-24));
                        }
                    } else if (reg == 411) { //dh
                        ArrayList<Integer> dh = new ArrayList<>();
                        for (int i = 16; i < Registers.edx.size()-8; i++) {
                            dh.add(Registers.ebx.get(i));
                        }
                        dh = Functions.and(dh,value);
                        for (int i = 16; i < Registers.edx.size()-8; i++) {
                            Registers.edx.set(i, dh.get(i-16));
                        }
                    } else if (reg == 412) { //dl
                        ArrayList<Integer> dl = new ArrayList<>();
                        for (int i = 24; i < Registers.edx.size(); i++) {
                            dl.add(Registers.ebx.get(i));
                        }
                        dl = Functions.and(dl,value);
                        for (int i = 24; i < Registers.edx.size(); i++) {
                            Registers.edx.set(i, dl.get(i-24));
                        }
                    }
                } else if (instruction == 7) { //or
                    if (reg == 1) { //eax
                        Registers.eax = Functions.or(Registers.eax, value);
                    } else if (reg == 2) { //ebx
                        Registers.ebx = Functions.or(Registers.ebx, value);
                    } else if (reg == 3) { //ecx
                        Registers.ecx = Functions.or(Registers.ecx, value);

                    } else if (reg == 4) { //edx
                        Registers.edx = Functions.or(Registers.edx, value);

                    } else if (reg == 5) { //esi
                        Registers.esi = Functions.or(Registers.esi, value);

                    } else if (reg == 6) { //edi
                        Registers.edi = Functions.or(Registers.edi, value);

                    } else if (reg == 7) { //esp
                        Registers.esp = Functions.or(Registers.esp, value);

                    } else if (reg == 8) { //ebp
                        Registers.ebp = Functions.or(Registers.ebp, value);
                    } else if (reg == 11) { //ax
                        ArrayList<Integer> ax = new ArrayList<>();
                        for (int i = 16; i < Registers.eax.size(); i++) {
                            ax.add(Registers.eax.get(i));
                        }
                        ax = Functions.or(ax,value);
                        for (int i = 16; i < Registers.eax.size(); i++) {
                            Registers.eax.set(i, ax.get(i-16));
                        }
                    } else if (reg == 21) { //bx
                        ArrayList<Integer> bx = new ArrayList<>();
                        for (int i = 16; i < Registers.ebx.size(); i++) {
                            bx.add(Registers.ebx.get(i));
                        }
                        bx = Functions.or(bx,value);
                        for (int i = 16; i < Registers.ebx.size(); i++) {
                            Registers.ebx.set(i, bx.get(i-16));
                        }
                    } else if (reg == 31) { //cx
                        ArrayList<Integer> cx = new ArrayList<>();
                        for (int i = 16; i < Registers.ecx.size(); i++) {
                            cx.add(Registers.ecx.get(i));
                        }
                        cx = Functions.or(cx,value);
                        for (int i = 16; i < Registers.ecx.size(); i++) {
                            Registers.ecx.set(i, cx.get(i-16));
                        }
                    } else if (reg == 41) { //dx
                        ArrayList<Integer> dx = new ArrayList<>();
                        for (int i = 16; i < Registers.edx.size(); i++) {
                            dx.add(Registers.edx.get(i));
                        }
                        dx = Functions.or(dx,value);
                        for (int i = 16; i < Registers.edx.size(); i++) {
                            Registers.edx.set(i, dx.get(i-16));
                        }
                    } else if (reg == 51) { //si
                        ArrayList<Integer> si = new ArrayList<>();
                        for (int i = 16; i < Registers.esi.size(); i++) {
                            si.add(Registers.esi.get(i));
                        }
                        si = Functions.or(si,value);
                        for (int i = 16; i < Registers.esi.size(); i++) {
                            Registers.esi.set(i, si.get(i-16));
                        }
                    } else if (reg == 61) { //di
                        ArrayList<Integer> di = new ArrayList<>();
                        for (int i = 16; i < Registers.edi.size(); i++) {
                            di.add(Registers.edx.get(i));
                        }
                        di = Functions.or(di,value);
                        for (int i = 16; i < Registers.edi.size(); i++) {
                            Registers.edi.set(i, di.get(i-16));
                        }
                    } else if (reg == 71) { //sp
                        ArrayList<Integer> sp = new ArrayList<>();
                        for (int i = 16; i < Registers.esp.size(); i++) {
                            sp.add(Registers.esp.get(i));
                        }
                        sp = Functions.or(sp,value);
                        for (int i = 16; i < Registers.esp.size(); i++) {
                            Registers.esp.set(i, sp.get(i-16));
                        }
                    } else if (reg == 81) { //bp
                        ArrayList<Integer> bp = new ArrayList<>();
                        for (int i = 16; i < Registers.ebp.size(); i++) {
                            bp.add(Registers.ebp.get(i));
                        }
                        bp = Functions.or(bp,value);
                        for (int i = 16; i < Registers.ebp.size(); i++) {
                            Registers.ebp.set(i, bp.get(i-16));
                        }
                    } else if (reg == 111) { //ah
                        ArrayList<Integer> ah = new ArrayList<>();
                        for (int i = 16; i < Registers.eax.size()-8; i++) {
                            ah.add(Registers.eax.get(i));
                        }
                        ah = Functions.or(ah,value);
                        for (int i = 16; i < Registers.eax.size()-8; i++) {
                            Registers.eax.set(i, ah.get(i-16));
                        }
                    } else if (reg == 112) { //al
                        ArrayList<Integer> al = new ArrayList<>();
                        for (int i = 24; i < Registers.eax.size(); i++) {
                            al.add(Registers.eax.get(i));
                        }
                        al = Functions.or(al,value);
                        for (int i = 24; i < Registers.eax.size(); i++) {
                            Registers.eax.set(i, al.get(i-24));
                        }
                    } else if (reg == 211) { //bh
                        ArrayList<Integer> bh = new ArrayList<>();
                        for (int i = 16; i < Registers.ebx.size()-8; i++) {
                            bh.add(Registers.ebx.get(i));
                        }
                        bh = Functions.or(bh,value);
                        for (int i = 16; i < Registers.ebx.size()-8; i++) {
                            Registers.ebx.set(i, bh.get(i-16));
                        }
                    } else if (reg == 212) { //bl
                        ArrayList<Integer> bl = new ArrayList<>();
                        for (int i = 24; i < Registers.ebx.size(); i++) {
                            bl.add(Registers.ebx.get(i));
                        }
                        bl = Functions.or(bl,value);
                        for (int i = 24; i < Registers.ebx.size(); i++) {
                            Registers.ebx.set(i, bl.get(i-24));
                        }
                    } else if (reg == 311) { //ch
                        ArrayList<Integer> ch = new ArrayList<>();
                        for (int i = 16; i < Registers.ecx.size()-8; i++) {
                            ch.add(Registers.ecx.get(i));
                        }
                        ch = Functions.or(ch,value);
                        for (int i = 16; i < Registers.ecx.size()-8; i++) {
                            Registers.ecx.set(i, ch.get(i-16));
                        }
                    } else if (reg == 312) { //cl
                        ArrayList<Integer> cl = new ArrayList<>();
                        for (int i = 24; i < Registers.ecx.size(); i++) {
                            cl.add(Registers.ecx.get(i));
                        }
                        cl = Functions.or(cl,value);
                        for (int i = 24; i < Registers.ecx.size(); i++) {
                            Registers.ecx.set(i, cl.get(i-24));
                        }
                    } else if (reg == 411) { //dh
                        ArrayList<Integer> dh = new ArrayList<>();
                        for (int i = 16; i < Registers.edx.size()-8; i++) {
                            dh.add(Registers.ebx.get(i));
                        }
                        dh = Functions.or(dh,value);
                        for (int i = 16; i < Registers.edx.size()-8; i++) {
                            Registers.edx.set(i, dh.get(i-16));
                        }
                    } else if (reg == 412) { //dl
                        ArrayList<Integer> dl = new ArrayList<>();
                        for (int i = 24; i < Registers.edx.size(); i++) {
                            dl.add(Registers.ebx.get(i));
                        }
                        dl = Functions.or(dl,value);
                        for (int i = 24; i < Registers.edx.size(); i++) {
                            Registers.edx.set(i, dl.get(i-24));
                        }
                    }
                } else if (instruction == 8) { //xor
                    if (reg == 1) { //eax
                        Registers.eax = Functions.xor(Registers.eax, value);
                    } else if (reg == 2) { //ebx
                        Registers.ebx = Functions.xor(Registers.ebx, value);
                    } else if (reg == 3) { //ecx
                        Registers.ecx = Functions.xor(Registers.ecx, value);

                    } else if (reg == 4) { //edx
                        Registers.edx = Functions.xor(Registers.edx, value);

                    } else if (reg == 5) { //esi
                        Registers.esi = Functions.xor(Registers.esi, value);

                    } else if (reg == 6) { //edi
                        Registers.edi = Functions.xor(Registers.edi, value);

                    } else if (reg == 7) { //esp
                        Registers.esp = Functions.xor(Registers.esp, value);

                    } else if (reg == 8) { //ebp
                        Registers.ebp = Functions.xor(Registers.ebp, value);
                    } else if (reg == 11) { //ax
                        ArrayList<Integer> ax = new ArrayList<>();
                        for (int i = 16; i < Registers.eax.size(); i++) {
                            ax.add(Registers.eax.get(i));
                        }
                        ax = Functions.xor(ax,value);
                        for (int i = 16; i < Registers.eax.size(); i++) {
                            Registers.eax.set(i, ax.get(i-16));
                        }
                    } else if (reg == 21) { //bx
                        ArrayList<Integer> bx = new ArrayList<>();
                        for (int i = 16; i < Registers.ebx.size(); i++) {
                            bx.add(Registers.ebx.get(i));
                        }
                        bx = Functions.xor(bx,value);
                        for (int i = 16; i < Registers.ebx.size(); i++) {
                            Registers.ebx.set(i, bx.get(i-16));
                        }
                    } else if (reg == 31) { //cx
                        ArrayList<Integer> cx = new ArrayList<>();
                        for (int i = 16; i < Registers.ecx.size(); i++) {
                            cx.add(Registers.ecx.get(i));
                        }
                        cx = Functions.xor(cx,value);
                        for (int i = 16; i < Registers.ecx.size(); i++) {
                            Registers.ecx.set(i, cx.get(i-16));
                        }
                    } else if (reg == 41) { //dx
                        ArrayList<Integer> dx = new ArrayList<>();
                        for (int i = 16; i < Registers.edx.size(); i++) {
                            dx.add(Registers.edx.get(i));
                        }
                        dx = Functions.xor(dx,value);
                        for (int i = 16; i < Registers.edx.size(); i++) {
                            Registers.edx.set(i, dx.get(i-16));
                        }
                    } else if (reg == 51) { //si
                        ArrayList<Integer> si = new ArrayList<>();
                        for (int i = 16; i < Registers.esi.size(); i++) {
                            si.add(Registers.esi.get(i));
                        }
                        si = Functions.xor(si,value);
                        for (int i = 16; i < Registers.esi.size(); i++) {
                            Registers.esi.set(i, si.get(i-16));
                        }
                    } else if (reg == 61) { //di
                        ArrayList<Integer> di = new ArrayList<>();
                        for (int i = 16; i < Registers.edi.size(); i++) {
                            di.add(Registers.edx.get(i));
                        }
                        di = Functions.xor(di,value);
                        for (int i = 16; i < Registers.edi.size(); i++) {
                            Registers.edi.set(i, di.get(i-16));
                        }
                    } else if (reg == 71) { //sp
                        ArrayList<Integer> sp = new ArrayList<>();
                        for (int i = 16; i < Registers.esp.size(); i++) {
                            sp.add(Registers.esp.get(i));
                        }
                        sp = Functions.xor(sp,value);
                        for (int i = 16; i < Registers.esp.size(); i++) {
                            Registers.esp.set(i, sp.get(i-16));
                        }
                    } else if (reg == 81) { //bp
                        ArrayList<Integer> bp = new ArrayList<>();
                        for (int i = 16; i < Registers.ebp.size(); i++) {
                            bp.add(Registers.ebp.get(i));
                        }
                        bp = Functions.xor(bp,value);
                        for (int i = 16; i < Registers.ebp.size(); i++) {
                            Registers.ebp.set(i, bp.get(i-16));
                        }
                    } else if (reg == 111) { //ah
                        ArrayList<Integer> ah = new ArrayList<>();
                        for (int i = 16; i < Registers.eax.size()-8; i++) {
                            ah.add(Registers.eax.get(i));
                        }
                        ah = Functions.xor(ah,value);
                        for (int i = 16; i < Registers.eax.size()-8; i++) {
                            Registers.eax.set(i, ah.get(i-16));
                        }
                    } else if (reg == 112) { //al
                        ArrayList<Integer> al = new ArrayList<>();
                        for (int i = 24; i < Registers.eax.size(); i++) {
                            al.add(Registers.eax.get(i));
                        }
                        al = Functions.xor(al,value);
                        for (int i = 24; i < Registers.eax.size(); i++) {
                            Registers.eax.set(i, al.get(i-24));
                        }
                    } else if (reg == 211) { //bh
                        ArrayList<Integer> bh = new ArrayList<>();
                        for (int i = 16; i < Registers.ebx.size()-8; i++) {
                            bh.add(Registers.ebx.get(i));
                        }
                        bh = Functions.xor(bh,value);
                        for (int i = 16; i < Registers.ebx.size()-8; i++) {
                            Registers.ebx.set(i, bh.get(i-16));
                        }
                    } else if (reg == 212) { //bl
                        ArrayList<Integer> bl = new ArrayList<>();
                        for (int i = 24; i < Registers.ebx.size(); i++) {
                            bl.add(Registers.ebx.get(i));
                        }
                        bl = Functions.xor(bl,value);
                        for (int i = 24; i < Registers.ebx.size(); i++) {
                            Registers.ebx.set(i, bl.get(i-24));
                        }
                    } else if (reg == 311) { //ch
                        ArrayList<Integer> ch = new ArrayList<>();
                        for (int i = 16; i < Registers.ecx.size()-8; i++) {
                            ch.add(Registers.ecx.get(i));
                        }
                        ch = Functions.xor(ch,value);
                        for (int i = 16; i < Registers.ecx.size()-8; i++) {
                            Registers.ecx.set(i, ch.get(i-16));
                        }
                    } else if (reg == 312) { //cl
                        ArrayList<Integer> cl = new ArrayList<>();
                        for (int i = 24; i < Registers.ecx.size(); i++) {
                            cl.add(Registers.ecx.get(i));
                        }
                        cl = Functions.xor(cl,value);
                        for (int i = 24; i < Registers.ecx.size(); i++) {
                            Registers.ecx.set(i, cl.get(i-24));
                        }
                    } else if (reg == 411) { //dh
                        ArrayList<Integer> dh = new ArrayList<>();
                        for (int i = 16; i < Registers.edx.size()-8; i++) {
                            dh.add(Registers.ebx.get(i));
                        }
                        dh = Functions.xor(dh,value);
                        for (int i = 16; i < Registers.edx.size()-8; i++) {
                            Registers.edx.set(i, dh.get(i-16));
                        }
                    } else if (reg == 412) { //dl
                        ArrayList<Integer> dl = new ArrayList<>();
                        for (int i = 24; i < Registers.edx.size(); i++) {
                            dl.add(Registers.ebx.get(i));
                        }
                        dl = Functions.xor(dl,value);
                        for (int i = 24; i < Registers.edx.size(); i++) {
                            Registers.edx.set(i, dl.get(i-24));
                        }
                    }
                }
            }
            else {
                ArrayList<Integer> ax = new ArrayList<>();
                for (int i = 16; i < Registers.eax.size(); i++) {
                    ax.add(Registers.eax.get(i));
                }
                ArrayList<Integer> bx = new ArrayList<>();
                for (int i = 16; i < Registers.ebx.size(); i++) {
                    bx.add(Registers.ebx.get(i));
                }
                ArrayList<Integer> cx = new ArrayList<>();
                for (int i = 16; i < Registers.ecx.size(); i++) {
                    cx.add(Registers.ecx.get(i));
                }
                ArrayList<Integer> dx = new ArrayList<>();
                for (int i = 16; i < Registers.edx.size(); i++) {
                    dx.add(Registers.edx.get(i));
                }
                ArrayList<Integer> si = new ArrayList<>();
                for (int i = 16; i < Registers.esi.size(); i++) {
                    si.add(Registers.esi.get(i));
                }
                ArrayList<Integer> di = new ArrayList<>();
                for (int i = 16; i < Registers.edi.size(); i++) {
                    di.add(Registers.edx.get(i));
                }
                ArrayList<Integer> sp = new ArrayList<>();
                for (int i = 16; i < Registers.esp.size(); i++) {
                    sp.add(Registers.esp.get(i));
                }
                ArrayList<Integer> bp = new ArrayList<>();
                for (int i = 16; i < Registers.ebp.size(); i++) {
                    bp.add(Registers.ebp.get(i));
                }
                ArrayList<Integer> ah = new ArrayList<>();
                for (int i = 16; i < Registers.eax.size()-8; i++) {
                    ah.add(Registers.eax.get(i));
                }
                ArrayList<Integer> al = new ArrayList<>();
                for (int i = 24; i < Registers.eax.size(); i++) {
                    al.add(Registers.eax.get(i));
                }
                if (source == -1) { //eax
                    if (instruction == 1) { //mov
                        if (reg == 1) { //eax
                            Registers.eax = Functions.mov(Registers.eax, Registers.eax);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.mov(Registers.ebx, Registers.eax);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.mov(Registers.ecx, Registers.eax);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.mov(Registers.edx, Registers.eax);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.mov(Registers.esi, Registers.eax);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.mov(Registers.edi, Registers.eax);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.mov(Registers.esp, Registers.eax);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.mov(Registers.ebp, Registers.eax);
                        }
                    } else if (instruction == 2) { //add
                        if (reg == 1) { //eax
                            Registers.eax = Functions.add(Registers.eax, Registers.eax);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.add(Registers.ebx, Registers.eax);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.add(Registers.ecx, Registers.eax);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.add(Registers.edx, Registers.eax);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.add(Registers.esi, Registers.eax);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.add(Registers.edi, Registers.eax);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.add(Registers.esp, Registers.eax);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.add(Registers.ebp, Registers.eax);
                        }
                    } else if (instruction == 3) { //sub
                        if (reg == 1) { //eax
                            Registers.eax = Functions.sub(Registers.eax, Registers.eax);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.sub(Registers.ebx, Registers.eax);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.sub(Registers.ecx, Registers.eax);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.sub(Registers.edx, Registers.eax);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.sub(Registers.esi, Registers.eax);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.sub(Registers.edi, Registers.eax);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.sub(Registers.esp, Registers.eax);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.sub(Registers.ebp, Registers.eax);
                        }
                    } else if (instruction == 6) { //
                        if (reg == 1) { //eax
                            Registers.eax = Functions.and(Registers.eax, Registers.eax);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.and(Registers.ebx, Registers.eax);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.and(Registers.ecx, Registers.eax);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.and(Registers.edx, Registers.eax);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.and(Registers.esi, Registers.eax);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.and(Registers.edi, Registers.eax);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.and(Registers.esp, Registers.eax);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.and(Registers.ebp, Registers.eax);
                        }
                    } else if (instruction == 7) { //or
                        if (reg == 1) { //eax
                            Registers.eax = Functions.or(Registers.eax, Registers.eax);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.or(Registers.ebx, Registers.eax);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.or(Registers.ecx, Registers.eax);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.or(Registers.edx, Registers.eax);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.or(Registers.esi, Registers.eax);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.or(Registers.edi, Registers.eax);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.or(Registers.esp, Registers.eax);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.or(Registers.ebp, Registers.eax);
                        }
                    } else if (instruction == 8) { //xor
                        if (reg == 1) { //eax
                            Registers.eax = Functions.xor(Registers.eax, Registers.eax);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.xor(Registers.ebx, Registers.eax);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.xor(Registers.ecx, Registers.eax);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.xor(Registers.edx, Registers.eax);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.xor(Registers.esi, Registers.eax);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.xor(Registers.edi, Registers.eax);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.xor(Registers.esp, Registers.eax);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.xor(Registers.ebp, Registers.eax);
                        }
                    }

                }else if (source == -2) { //ebx
                    if (instruction == 1) { //mov
                        if (reg == 1) { //eax
                            Registers.eax = Functions.mov(Registers.eax, Registers.ebx);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.mov(Registers.ebx, Registers.ebx);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.mov(Registers.ecx, Registers.ebx);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.mov(Registers.edx, Registers.ebx);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.mov(Registers.esi, Registers.ebx);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.mov(Registers.edi, Registers.ebx);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.mov(Registers.esp, Registers.ebx);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.mov(Registers.ebp, Registers.ebx);
                        }
                    } else if (instruction == 2) { //add
                        if (reg == 1) { //eax
                            Registers.eax = Functions.add(Registers.eax, Registers.ebx);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.add(Registers.ebx, Registers.ebx);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.add(Registers.ecx, Registers.ebx);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.add(Registers.edx, Registers.ebx);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.add(Registers.esi, Registers.ebx);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.add(Registers.edi, Registers.ebx);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.add(Registers.esp, Registers.ebx);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.add(Registers.ebp, Registers.ebx);
                        }
                    } else if (instruction == 3) { //sub
                        if (reg == 1) { //eax
                            Registers.eax = Functions.sub(Registers.eax, Registers.ebx);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.sub(Registers.ebx, Registers.ebx);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.sub(Registers.ecx, Registers.ebx);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.sub(Registers.edx, Registers.ebx);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.sub(Registers.esi, Registers.ebx);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.sub(Registers.edi, Registers.ebx);

                        } else if (reg == 7) { //ebx
                            Registers.esp = Functions.sub(Registers.esp, Registers.ebx);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.sub(Registers.ebp, Registers.ebx);
                        }
                    } else if (instruction == 6) { //
                        if (reg == 1) { //eax
                            Registers.eax = Functions.and(Registers.eax, Registers.ebx);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.and(Registers.ebx, Registers.ebx);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.and(Registers.ecx, Registers.ebx);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.and(Registers.edx, Registers.ebx);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.and(Registers.esi, Registers.ebx);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.and(Registers.edi, Registers.ebx);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.and(Registers.esp, Registers.ebx);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.and(Registers.ebp, Registers.ebx);
                        }
                    } else if (instruction == 7) { //or
                        if (reg == 1) { //eax
                            Registers.eax = Functions.or(Registers.eax, Registers.ebx);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.or(Registers.ebx, Registers.ebx);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.or(Registers.ecx, Registers.ebx);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.or(Registers.edx, Registers.ebx);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.or(Registers.esi, Registers.ebx);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.or(Registers.edi, Registers.ebx);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.or(Registers.esp, Registers.ebx);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.or(Registers.ebp, Registers.ebx);
                        }
                    } else if (instruction == 8) { //xor
                        if (reg == 1) { //eax
                            Registers.eax = Functions.xor(Registers.eax, Registers.ebx);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.xor(Registers.ebx, Registers.ebx);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.xor(Registers.ecx, Registers.ebx);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.xor(Registers.edx, Registers.ebx);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.xor(Registers.esi, Registers.ebx);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.xor(Registers.edi, Registers.ebx);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.xor(Registers.esp, Registers.ebx);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.xor(Registers.ebp, Registers.ebx);
                        }
                    }
                }else if (source == -3) { //ecx
                    if (instruction == 1) { //mov
                        if (reg == 1) { //eax
                            Registers.eax = Functions.mov(Registers.eax, Registers.ecx);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.mov(Registers.ebx, Registers.ecx);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.mov(Registers.ecx, Registers.ecx);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.mov(Registers.edx, Registers.ecx);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.mov(Registers.esi, Registers.ecx);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.mov(Registers.edi, Registers.ecx);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.mov(Registers.esp, Registers.ecx);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.mov(Registers.ebp, Registers.ecx);
                        }
                    } else if (instruction == 2) { //add
                        if (reg == 1) { //eax
                            Registers.eax = Functions.add(Registers.eax, Registers.ecx);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.add(Registers.ebx, Registers.ecx);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.add(Registers.ecx, Registers.ecx);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.add(Registers.edx, Registers.ecx);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.add(Registers.esi, Registers.ecx);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.add(Registers.edi, Registers.ecx);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.add(Registers.esp, Registers.ecx);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.add(Registers.ebp, Registers.ecx);
                        }
                    } else if (instruction == 3) { //sub
                        if (reg == 1) { //eax
                            Registers.eax = Functions.sub(Registers.eax, Registers.ecx);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.sub(Registers.ebx, Registers.ecx);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.sub(Registers.ecx, Registers.ecx);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.sub(Registers.edx, Registers.ecx);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.sub(Registers.esi, Registers.ecx);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.sub(Registers.edi, Registers.ecx);

                        } else if (reg == 7) { //ebx
                            Registers.esp = Functions.sub(Registers.esp, Registers.ecx);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.sub(Registers.ebp, Registers.ecx);
                        }
                    } else if (instruction == 6) { //
                        if (reg == 1) { //eax
                            Registers.eax = Functions.and(Registers.eax, Registers.ecx);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.and(Registers.ebx, Registers.ecx);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.and(Registers.ecx, Registers.ecx);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.and(Registers.edx, Registers.ecx);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.and(Registers.esi, Registers.ecx);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.and(Registers.edi, Registers.ecx);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.and(Registers.esp, Registers.ecx);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.and(Registers.ebp, Registers.ecx);
                        }
                    } else if (instruction == 7) { //or
                        if (reg == 1) { //eax
                            Registers.eax = Functions.or(Registers.eax, Registers.ecx);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.or(Registers.ebx, Registers.ecx);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.or(Registers.ecx, Registers.ecx);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.or(Registers.edx, Registers.ecx);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.or(Registers.esi, Registers.ecx);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.or(Registers.edi, Registers.ecx);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.or(Registers.esp, Registers.ecx);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.or(Registers.ebp, Registers.ecx);
                        }
                    } else if (instruction == 8) { //xor
                        if (reg == 1) { //eax
                            Registers.eax = Functions.xor(Registers.eax, Registers.ecx);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.xor(Registers.ebx, Registers.ecx);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.xor(Registers.ecx, Registers.ecx);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.xor(Registers.edx, Registers.ecx);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.xor(Registers.esi, Registers.ecx);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.xor(Registers.edi, Registers.ecx);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.xor(Registers.esp, Registers.ecx);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.xor(Registers.ebp, Registers.ecx);
                        }
                    }

                }else if (source == -4) { //edx
                    if (instruction == 1) { //mov
                        if (reg == 1) { //eax
                            Registers.eax = Functions.mov(Registers.eax, Registers.edx);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.mov(Registers.ebx, Registers.edx);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.mov(Registers.ecx, Registers.edx);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.mov(Registers.edx, Registers.edx);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.mov(Registers.esi, Registers.edx);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.mov(Registers.edi, Registers.edx);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.mov(Registers.esp, Registers.edx);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.mov(Registers.ebp, Registers.edx);
                        }
                    } else if (instruction == 2) { //add
                        if (reg == 1) { //eax
                            Registers.eax = Functions.add(Registers.eax, Registers.edx);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.add(Registers.ebx, Registers.edx);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.add(Registers.ecx, Registers.edx);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.add(Registers.edx, Registers.edx);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.add(Registers.esi, Registers.edx);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.add(Registers.edi, Registers.edx);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.add(Registers.esp, Registers.edx);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.add(Registers.ebp, Registers.edx);
                        }
                    } else if (instruction == 3) { //sub
                        if (reg == 1) { //eax
                            Registers.eax = Functions.sub(Registers.eax, Registers.edx);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.sub(Registers.ebx, Registers.edx);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.sub(Registers.ecx, Registers.edx);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.sub(Registers.edx, Registers.edx);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.sub(Registers.esi, Registers.edx);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.sub(Registers.edi, Registers.edx);

                        } else if (reg == 7) { //ebx
                            Registers.esp = Functions.sub(Registers.esp, Registers.edx);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.sub(Registers.ebp, Registers.edx);
                        }
                    } else if (instruction == 6) { //
                        if (reg == 1) { //eax
                            Registers.eax = Functions.and(Registers.eax, Registers.edx);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.and(Registers.ebx, Registers.edx);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.and(Registers.ecx, Registers.edx);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.and(Registers.edx, Registers.edx);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.and(Registers.esi, Registers.edx);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.and(Registers.edi, Registers.edx);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.and(Registers.esp, Registers.edx);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.and(Registers.ebp, Registers.edx);
                        }
                    } else if (instruction == 7) { //or
                        if (reg == 1) { //eax
                            Registers.eax = Functions.or(Registers.eax, Registers.edx);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.or(Registers.ebx, Registers.edx);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.or(Registers.ecx, Registers.edx);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.or(Registers.edx, Registers.edx);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.or(Registers.esi, Registers.edx);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.or(Registers.edi, Registers.edx);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.or(Registers.esp, Registers.edx);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.or(Registers.ebp, Registers.edx);
                        }
                    } else if (instruction == 8) { //xor
                        if (reg == 1) { //eax
                            Registers.eax = Functions.xor(Registers.eax, Registers.edx);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.xor(Registers.ebx, Registers.edx);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.xor(Registers.ecx, Registers.edx);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.xor(Registers.edx, Registers.edx);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.xor(Registers.esi, Registers.edx);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.xor(Registers.edi, Registers.edx);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.xor(Registers.esp, Registers.edx);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.xor(Registers.ebp, Registers.edx);
                        }
                    }

                }else if (source == -5) { //esi
                    if (instruction == 1) { //mov
                        if (reg == 1) { //eax
                            Registers.eax = Functions.mov(Registers.eax, Registers.esi);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.mov(Registers.ebx, Registers.esi);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.mov(Registers.ecx, Registers.esi);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.mov(Registers.edx, Registers.esi);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.mov(Registers.esi, Registers.esi);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.mov(Registers.edi, Registers.esi);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.mov(Registers.esp, Registers.esi);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.mov(Registers.ebp, Registers.esi);
                        }
                    } else if (instruction == 2) { //add
                        if (reg == 1) { //eax
                            Registers.eax = Functions.add(Registers.eax, Registers.esi);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.add(Registers.ebx, Registers.esi);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.add(Registers.ecx, Registers.esi);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.add(Registers.edx, Registers.esi);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.add(Registers.esi, Registers.esi);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.add(Registers.edi, Registers.esi);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.add(Registers.esp, Registers.esi);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.add(Registers.ebp, Registers.esi);
                        }
                    } else if (instruction == 3) { //sub
                        if (reg == 1) { //eax
                            Registers.eax = Functions.sub(Registers.eax, Registers.esi);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.sub(Registers.ebx, Registers.esi);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.sub(Registers.ecx, Registers.esi);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.sub(Registers.edx, Registers.esi);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.sub(Registers.esi, Registers.esi);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.sub(Registers.edi, Registers.esi);

                        } else if (reg == 7) { //ebx
                            Registers.esp = Functions.sub(Registers.esp, Registers.esi);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.sub(Registers.ebp, Registers.esi);
                        }
                    } else if (instruction == 6) { //
                        if (reg == 1) { //eax
                            Registers.eax = Functions.and(Registers.eax, Registers.esi);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.and(Registers.ebx, Registers.esi);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.and(Registers.ecx, Registers.esi);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.and(Registers.edx, Registers.esi);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.and(Registers.esi, Registers.esi);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.and(Registers.edi, Registers.esi);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.and(Registers.esp, Registers.esi);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.and(Registers.ebp, Registers.esi);
                        }
                    } else if (instruction == 7) { //or
                        if (reg == 1) { //eax
                            Registers.eax = Functions.or(Registers.eax, Registers.esi);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.or(Registers.ebx, Registers.esi);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.or(Registers.ecx, Registers.esi);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.or(Registers.edx, Registers.esi);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.or(Registers.esi, Registers.esi);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.or(Registers.edi, Registers.esi);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.or(Registers.esp, Registers.esi);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.or(Registers.ebp, Registers.esi);
                        }
                    } else if (instruction == 8) { //xor
                        if (reg == 1) { //eax
                            Registers.eax = Functions.xor(Registers.eax, Registers.esi);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.xor(Registers.ebx, Registers.esi);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.xor(Registers.ecx, Registers.esi);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.xor(Registers.edx, Registers.esi);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.xor(Registers.esi, Registers.esi);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.xor(Registers.edi, Registers.esi);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.xor(Registers.esp, Registers.esi);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.xor(Registers.ebp, Registers.esi);
                        }
                    }

                }else if (source == -6) { //edi
                    if (instruction == 1) { //mov
                        if (reg == 1) { //eax
                            Registers.eax = Functions.mov(Registers.eax, Registers.edi);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.mov(Registers.ebx, Registers.edi);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.mov(Registers.ecx, Registers.edi);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.mov(Registers.edx, Registers.edi);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.mov(Registers.esi, Registers.edi);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.mov(Registers.edi, Registers.edi);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.mov(Registers.esp, Registers.edi);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.mov(Registers.ebp, Registers.edi);
                        }
                    } else if (instruction == 2) { //add
                        if (reg == 1) { //eax
                            Registers.eax = Functions.add(Registers.eax, Registers.edi);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.add(Registers.ebx, Registers.edi);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.add(Registers.ecx, Registers.edi);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.add(Registers.edx, Registers.edi);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.add(Registers.esi, Registers.edi);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.add(Registers.edi, Registers.edi);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.add(Registers.esp, Registers.edi);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.add(Registers.ebp, Registers.edi);
                        }
                    } else if (instruction == 3) { //sub
                        if (reg == 1) { //eax
                            Registers.eax = Functions.sub(Registers.eax, Registers.edi);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.sub(Registers.ebx, Registers.edi);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.sub(Registers.ecx, Registers.edi);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.sub(Registers.edx, Registers.edi);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.sub(Registers.esi, Registers.edi);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.sub(Registers.edi, Registers.edi);

                        } else if (reg == 7) { //ebx
                            Registers.esp = Functions.sub(Registers.esp, Registers.edi);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.sub(Registers.ebp, Registers.edi);
                        }
                    } else if (instruction == 6) { //
                        if (reg == 1) { //eax
                            Registers.eax = Functions.and(Registers.eax, Registers.edi);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.and(Registers.ebx, Registers.edi);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.and(Registers.ecx, Registers.edi);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.and(Registers.edx, Registers.edi);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.and(Registers.esi, Registers.edi);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.and(Registers.edi, Registers.edi);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.and(Registers.esp, Registers.edi);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.and(Registers.ebp, Registers.edi);
                        }
                    } else if (instruction == 7) { //or
                        if (reg == 1) { //eax
                            Registers.eax = Functions.or(Registers.eax, Registers.edi);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.or(Registers.ebx, Registers.edi);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.or(Registers.ecx, Registers.edi);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.or(Registers.edx, Registers.edi);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.or(Registers.esi, Registers.edi);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.or(Registers.edi, Registers.edi);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.or(Registers.esp, Registers.edi);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.or(Registers.ebp, Registers.edi);
                        }
                    } else if (instruction == 8) { //xor
                        if (reg == 1) { //eax
                            Registers.eax = Functions.xor(Registers.eax, Registers.edi);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.xor(Registers.ebx, Registers.edi);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.xor(Registers.ecx, Registers.edi);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.xor(Registers.edx, Registers.edi);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.xor(Registers.esi, Registers.edi);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.xor(Registers.edi, Registers.edi);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.xor(Registers.esp, Registers.edi);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.xor(Registers.ebp, Registers.edi);
                        }
                    }

                }else if (source == -7) { //esp
                    if (instruction == 1) { //mov
                        if (reg == 1) { //eax
                            Registers.eax = Functions.mov(Registers.eax, Registers.esp);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.mov(Registers.ebx, Registers.esp);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.mov(Registers.ecx, Registers.esp);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.mov(Registers.edx, Registers.esp);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.mov(Registers.esi, Registers.esp);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.mov(Registers.edi, Registers.esp);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.mov(Registers.esp, Registers.esp);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.mov(Registers.ebp, Registers.esp);
                        }
                    } else if (instruction == 2) { //add
                        if (reg == 1) { //eax
                            Registers.eax = Functions.add(Registers.eax, Registers.esp);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.add(Registers.ebx, Registers.esp);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.add(Registers.ecx, Registers.esp);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.add(Registers.edx, Registers.esp);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.add(Registers.esi, Registers.esp);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.add(Registers.edi, Registers.esp);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.add(Registers.esp, Registers.esp);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.add(Registers.ebp, Registers.esp);
                        }
                    } else if (instruction == 3) { //sub
                        if (reg == 1) { //eax
                            Registers.eax = Functions.sub(Registers.eax, Registers.esp);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.sub(Registers.ebx, Registers.esp);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.sub(Registers.ecx, Registers.esp);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.sub(Registers.edx, Registers.esp);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.sub(Registers.esi, Registers.esp);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.sub(Registers.edi, Registers.esp);

                        } else if (reg == 7) { //ebx
                            Registers.esp = Functions.sub(Registers.esp, Registers.esp);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.sub(Registers.ebp, Registers.esp);
                        }
                    } else if (instruction == 6) { //
                        if (reg == 1) { //eax
                            Registers.eax = Functions.and(Registers.eax, Registers.esp);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.and(Registers.ebx, Registers.esp);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.and(Registers.ecx, Registers.esp);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.and(Registers.edx, Registers.esp);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.and(Registers.esi, Registers.esp);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.and(Registers.edi, Registers.esp);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.and(Registers.esp, Registers.esp);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.and(Registers.ebp, Registers.esp);
                        }
                    } else if (instruction == 7) { //or
                        if (reg == 1) { //eax
                            Registers.eax = Functions.or(Registers.eax, Registers.esp);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.or(Registers.ebx, Registers.esp);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.or(Registers.ecx, Registers.esp);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.or(Registers.edx, Registers.esp);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.or(Registers.esi, Registers.esp);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.or(Registers.edi, Registers.esp);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.or(Registers.esp, Registers.esp);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.or(Registers.ebp, Registers.esp);
                        }
                    } else if (instruction == 8) { //xor
                        if (reg == 1) { //eax
                            Registers.eax = Functions.xor(Registers.eax, Registers.esp);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.xor(Registers.ebx, Registers.esp);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.xor(Registers.ecx, Registers.esp);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.xor(Registers.edx, Registers.esp);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.xor(Registers.esi, Registers.esp);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.xor(Registers.edi, Registers.esp);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.xor(Registers.esp, Registers.esp);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.xor(Registers.ebp, Registers.esp);
                        }
                    }

                }else if (source == -8) { //ebp
                    if (instruction == 1) { //mov
                        if (reg == 1) { //eax
                            Registers.eax = Functions.mov(Registers.eax, Registers.ebp);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.mov(Registers.ebx, Registers.ebp);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.mov(Registers.ecx, Registers.ebp);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.mov(Registers.edx, Registers.ebp);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.mov(Registers.esi, Registers.ebp);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.mov(Registers.edi, Registers.ebp);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.mov(Registers.esp, Registers.ebp);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.mov(Registers.ebp, Registers.ebp);
                        }
                    } else if (instruction == 2) { //add
                        if (reg == 1) { //eax
                            Registers.eax = Functions.add(Registers.eax, Registers.ebp);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.add(Registers.ebx, Registers.ebp);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.add(Registers.ecx, Registers.ebp);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.add(Registers.edx, Registers.ebp);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.add(Registers.esi, Registers.ebp);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.add(Registers.edi, Registers.ebp);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.add(Registers.esp, Registers.ebp);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.add(Registers.ebp, Registers.ebp);
                        }
                    } else if (instruction == 3) { //sub
                        if (reg == 1) { //eax
                            Registers.eax = Functions.sub(Registers.eax, Registers.ebp);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.sub(Registers.ebx, Registers.ebp);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.sub(Registers.ecx, Registers.ebp);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.sub(Registers.edx, Registers.ebp);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.sub(Registers.esi, Registers.ebp);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.sub(Registers.edi, Registers.ebp);

                        } else if (reg == 7) { //ebx
                            Registers.esp = Functions.sub(Registers.esp, Registers.ebp);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.sub(Registers.ebp, Registers.ebp);
                        }
                    } else if (instruction == 6) { //
                        if (reg == 1) { //eax
                            Registers.eax = Functions.and(Registers.eax, Registers.ebp);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.and(Registers.ebx, Registers.ebp);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.and(Registers.ecx, Registers.ebp);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.and(Registers.edx, Registers.ebp);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.and(Registers.esi, Registers.ebp);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.and(Registers.edi, Registers.ebp);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.and(Registers.esp, Registers.ebp);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.and(Registers.ebp, Registers.ebp);
                        }
                    } else if (instruction == 7) { //or
                        if (reg == 1) { //eax
                            Registers.eax = Functions.or(Registers.eax, Registers.ebp);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.or(Registers.ebx, Registers.ebp);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.or(Registers.ecx, Registers.ebp);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.or(Registers.edx, Registers.ebp);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.or(Registers.esi, Registers.ebp);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.or(Registers.edi, Registers.ebp);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.or(Registers.esp, Registers.ebp);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.or(Registers.ebp, Registers.ebp);
                        }
                    } else if (instruction == 8) { //xor
                        if (reg == 1) { //eax
                            Registers.eax = Functions.xor(Registers.eax, Registers.ebp);
                        } else if (reg == 2) { //ebx
                            Registers.ebx = Functions.xor(Registers.ebx, Registers.ebp);
                        } else if (reg == 3) { //ecx
                            Registers.ecx = Functions.xor(Registers.ecx, Registers.ebp);

                        } else if (reg == 4) { //edx
                            Registers.edx = Functions.xor(Registers.edx, Registers.ebp);

                        } else if (reg == 5) { //esi
                            Registers.esi = Functions.xor(Registers.esi, Registers.ebp);

                        } else if (reg == 6) { //edi
                            Registers.edi = Functions.xor(Registers.edi, Registers.ebp);

                        } else if (reg == 7) { //esp
                            Registers.esp = Functions.xor(Registers.esp, Registers.ebp);

                        } else if (reg == 8) { //ebp
                            Registers.ebp = Functions.xor(Registers.ebp, Registers.ebp);
                        }
                    }
                }else if (source == -11) { //ax
                    if (instruction == 1) { //mov
                        if (reg == 11) { //ax
                            ax = Functions.mov(ax,ax);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.mov(bx,ax);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.mov(cx,ax);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.mov(dx,ax);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.mov(si,ax);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.mov(di,ax);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.mov(sp,ax);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp

                            bp = Functions.mov(bp,ax);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 2) { //add
                        if (reg == 11) { //ax
                            ax = Functions.add(ax,ax);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.add(bx,ax);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.add(cx,ax);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.add(dx,ax);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.add(si,ax);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.add(di,value);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.add(sp,ax);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.add(bp,ax);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 3) { //sub
                        if (reg == 11) { //ax
                            ax = Functions.sub(ax,ax);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.sub(bx,ax);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.sub(cx,ax);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.sub(dx,ax);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.sub(si,ax);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.sub(di,ax);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.sub(sp,ax);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.sub(bp,ax);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 6) { //
                        if (reg == 11) { //ax
                            ax = Functions.and(ax,ax);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.and(bx,ax);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.and(cx,ax);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.and(dx,ax);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.and(si,ax);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.and(di,ax);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.and(sp,ax);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.and(bp,ax);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 7) { //or
                        if (reg == 11) { //ax
                            ax = Functions.or(ax,ax);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.or(bx,ax);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.or(cx,ax);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.or(dx,ax);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.or(si,ax);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.or(di,ax);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.or(sp,ax);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.or(bp,ax);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 8) { //xor
                        if (reg == 11) { //ax
                            ax = Functions.xor(ax,ax);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.xor(bx,ax);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.xor(cx,ax);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.xor(dx,ax);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.xor(si,ax);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.xor(di,ax);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.xor(sp,ax);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.xor(bp,ax);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    }
                }else if (source == -21) { //bx
                    if (instruction == 1) { //mov
                        if (reg == 11) { //ax
                            ax = Functions.mov(ax,bx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.mov(bx,bx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.mov(cx,bx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.mov(dx,bx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.mov(si,bx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.mov(di,bx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.mov(sp,bx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.mov(bp,bx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 2) { //add
                        if (reg == 11) { //ax
                            ax = Functions.add(ax,bx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.add(bx,bx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.add(cx,bx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.add(dx,bx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.add(si,bx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.add(di,bx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.add(sp,bx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.add(bp,bx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 3) { //sub
                        if (reg == 11) { //ax
                            ax = Functions.sub(ax,bx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.sub(bx,bx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.sub(cx,bx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.sub(dx,bx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.sub(si,bx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.sub(di,bx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.sub(sp,bx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.sub(bp,bx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 6) { //
                        if (reg == 11) { //ax
                            ax = Functions.and(ax,bx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.and(bx,bx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.and(cx,bx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.and(dx,bx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.and(si,bx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.and(di,bx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp

                            sp = Functions.and(sp,bx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.and(bp,bx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 7) { //or
                        if (reg == 11) { //ax
                            ax = Functions.or(ax,bx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.or(bx,ax);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.or(cx,bx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.or(dx,bx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.or(si,bx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.or(di,bx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.or(sp,bx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.or(bp,bx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 8) { //xor
                        if (reg == 11) { //ax
                            ax = Functions.xor(ax,ax);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.xor(bx,bx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.xor(cx,bx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.xor(dx,bx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.xor(si,bx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.xor(di,bx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.xor(sp,bx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.xor(bp,bx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    }
                }else if (source == -31) {//cx
                    if (instruction == 1) { //mov
                        if (reg == 11) { //ax
                            ax = Functions.mov(ax,cx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.mov(bx,cx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.mov(cx,cx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.mov(dx,cx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.mov(si,cx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.mov(di,cx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.mov(sp,cx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.mov(bp,cx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 2) { //add
                        if (reg == 11) { //ax
                            ax = Functions.add(ax,cx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.add(bx,cx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.add(cx,cx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.add(dx,cx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.add(si,cx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.add(di,cx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.add(sp,cx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.add(bp,cx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 3) { //sub
                        if (reg == 11) { //ax
                            ax = Functions.sub(ax,cx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.sub(bx,cx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.sub(cx,cx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.sub(dx,cx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.sub(si,cx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.sub(di,cx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.sub(sp,cx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.sub(bp,cx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 6) { //
                        if (reg == 11) { //ax
                            ax = Functions.and(ax,cx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.and(bx,cx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.and(cx,cx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.and(dx,cx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.and(si,cx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.and(di,cx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp

                            sp = Functions.and(sp,cx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.and(bp,cx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 7) { //or
                        if (reg == 11) { //ax
                            ax = Functions.or(ax,cx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.or(bx,cx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.or(cx,cx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.or(dx,cx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.or(si,cx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.or(di,cx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.or(sp,cx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.or(bp,cx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 8) { //xor
                        if (reg == 11) { //ax
                            ax = Functions.xor(ax,cx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.xor(bx,cx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.xor(cx,cx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.xor(dx,cx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.xor(si,cx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.xor(di,cx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.xor(sp,cx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.xor(bp,cx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    }
                }else if (source == -41) {//dx
                    if (instruction == 1) { //mov
                        if (reg == 11) { //ax
                            ax = Functions.mov(ax,dx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.mov(bx,dx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.mov(cx,dx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.mov(dx,dx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.mov(si,dx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.mov(di,dx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.mov(sp,dx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.mov(bp,dx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 2) { //add
                        if (reg == 11) { //ax
                            ax = Functions.add(ax,dx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.add(bx,dx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.add(cx,dx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.add(dx,dx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.add(si,dx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.add(di,dx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.add(sp,dx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.add(bp,dx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 3) { //sub
                        if (reg == 11) { //ax
                            ax = Functions.sub(ax,dx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.sub(bx,dx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.sub(cx,dx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.sub(dx,dx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.sub(si,dx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.sub(di,dx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.sub(sp,dx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.sub(bp,dx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 6) { //
                        if (reg == 11) { //ax
                            ax = Functions.and(ax,dx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.and(bx,dx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.and(cx,dx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.and(dx,dx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.and(si,dx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.and(di,dx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp

                            sp = Functions.and(sp,dx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.and(bp,dx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 7) { //or
                        if (reg == 11) { //ax
                            ax = Functions.or(ax,dx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.or(bx,dx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.or(cx,dx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.or(dx,dx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.or(si,dx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.or(di,dx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.or(sp,dx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.or(bp,dx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 8) { //xor
                        if (reg == 11) { //ax
                            ax = Functions.xor(ax,dx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.xor(bx,dx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.xor(cx,dx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.xor(dx,dx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.xor(si,dx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.xor(di,dx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.xor(sp,dx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.xor(bp,dx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    }
                }else if (source == -51) {//si
                    dx = si;
                    if (instruction == 1) { //mov
                        if (reg == 11) { //ax
                            ax = Functions.mov(ax,dx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.mov(bx,dx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.mov(cx,dx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.mov(dx,dx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.mov(si,dx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.mov(di,dx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.mov(sp,dx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.mov(bp,dx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 2) { //add
                        if (reg == 11) { //ax
                            ax = Functions.add(ax,dx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.add(bx,dx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.add(cx,dx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.add(dx,dx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.add(si,dx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.add(di,dx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.add(sp,dx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.add(bp,dx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 3) { //sub
                        if (reg == 11) { //ax
                            ax = Functions.sub(ax,dx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.sub(bx,dx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.sub(cx,dx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.sub(dx,dx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.sub(si,dx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.sub(di,dx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.sub(sp,dx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.sub(bp,dx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 6) { //
                        if (reg == 11) { //ax
                            ax = Functions.and(ax,dx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.and(bx,dx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.and(cx,dx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.and(dx,dx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.and(si,dx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.and(di,dx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp

                            sp = Functions.and(sp,dx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.and(bp,dx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 7) { //or
                        if (reg == 11) { //ax
                            ax = Functions.or(ax,dx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.or(bx,dx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.or(cx,dx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.or(dx,dx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.or(si,dx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.or(di,dx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.or(sp,dx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.or(bp,dx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 8) { //xor
                        if (reg == 11) { //ax
                            ax = Functions.xor(ax,dx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.xor(bx,dx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.xor(cx,dx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.xor(dx,dx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.xor(si,dx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.xor(di,dx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.xor(sp,dx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.xor(bp,dx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    }
                }else if (source == -61) {//di
                    di = dx;
                    if (instruction == 1) { //mov
                        if (reg == 11) { //ax
                            ax = Functions.mov(ax,dx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.mov(bx,dx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.mov(cx,dx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.mov(dx,dx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.mov(si,dx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.mov(di,dx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.mov(sp,dx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.mov(bp,dx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 2) { //add
                        if (reg == 11) { //ax
                            ax = Functions.add(ax,dx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.add(bx,dx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.add(cx,dx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.add(dx,dx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.add(si,dx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.add(di,dx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.add(sp,dx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.add(bp,dx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 3) { //sub
                        if (reg == 11) { //ax
                            ax = Functions.sub(ax,dx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.sub(bx,dx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.sub(cx,dx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.sub(dx,dx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.sub(si,dx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.sub(di,dx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.sub(sp,dx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.sub(bp,dx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 6) { //
                        if (reg == 11) { //ax
                            ax = Functions.and(ax,dx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.and(bx,dx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.and(cx,dx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.and(dx,dx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.and(si,dx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.and(di,dx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp

                            sp = Functions.and(sp,dx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.and(bp,dx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 7) { //or
                        if (reg == 11) { //ax
                            ax = Functions.or(ax,dx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.or(bx,dx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.or(cx,dx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.or(dx,dx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.or(si,dx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.or(di,dx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.or(sp,dx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.or(bp,dx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 8) { //xor
                        if (reg == 11) { //ax
                            ax = Functions.xor(ax,dx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.xor(bx,dx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.xor(cx,dx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.xor(dx,dx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.xor(si,dx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.xor(di,dx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.xor(sp,dx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.xor(bp,dx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    }

                }else if (source == -71) {//sp
                    dx = sp;
                    if (instruction == 1) { //mov
                        if (reg == 11) { //ax
                            ax = Functions.mov(ax,dx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.mov(bx,dx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.mov(cx,dx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.mov(dx,dx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.mov(si,dx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.mov(di,dx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.mov(sp,dx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.mov(bp,dx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 2) { //add
                        if (reg == 11) { //ax
                            ax = Functions.add(ax,dx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.add(bx,dx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.add(cx,dx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.add(dx,dx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.add(si,dx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.add(di,dx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.add(sp,dx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.add(bp,dx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 3) { //sub
                        if (reg == 11) { //ax
                            ax = Functions.sub(ax,dx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.sub(bx,dx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.sub(cx,dx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.sub(dx,dx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.sub(si,dx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.sub(di,dx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.sub(sp,dx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.sub(bp,dx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 6) { //
                        if (reg == 11) { //ax
                            ax = Functions.and(ax,dx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.and(bx,dx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.and(cx,dx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.and(dx,dx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.and(si,dx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.and(di,dx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp

                            sp = Functions.and(sp,dx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.and(bp,dx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 7) { //or
                        if (reg == 11) { //ax
                            ax = Functions.or(ax,dx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.or(bx,dx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.or(cx,dx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.or(dx,dx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.or(si,dx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.or(di,dx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.or(sp,dx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.or(bp,dx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 8) { //xor
                        if (reg == 11) { //ax
                            ax = Functions.xor(ax,dx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.xor(bx,dx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.xor(cx,dx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.xor(dx,dx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.xor(si,dx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.xor(di,dx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.xor(sp,dx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.xor(bp,dx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    }

                }else if (source == -81) {//bp
                    dx = bp;
                    if (instruction == 1) { //mov
                        if (reg == 11) { //ax
                            ax = Functions.mov(ax,dx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.mov(bx,dx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.mov(cx,dx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.mov(dx,dx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.mov(si,dx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.mov(di,dx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.mov(sp,dx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.mov(bp,dx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 2) { //add
                        if (reg == 11) { //ax
                            ax = Functions.add(ax,dx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.add(bx,dx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.add(cx,dx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.add(dx,dx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.add(si,dx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.add(di,dx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.add(sp,dx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.add(bp,dx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 3) { //sub
                        if (reg == 11) { //ax
                            ax = Functions.sub(ax,dx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.sub(bx,dx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.sub(cx,dx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.sub(dx,dx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.sub(si,dx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.sub(di,dx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.sub(sp,dx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.sub(bp,dx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 6) { //
                        if (reg == 11) { //ax
                            ax = Functions.and(ax,dx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.and(bx,dx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.and(cx,dx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.and(dx,dx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.and(si,dx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.and(di,dx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp

                            sp = Functions.and(sp,dx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.and(bp,dx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 7) { //or
                        if (reg == 11) { //ax
                            ax = Functions.or(ax,dx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.or(bx,dx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.or(cx,dx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.or(dx,dx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.or(si,dx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.or(di,dx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.or(sp,dx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.or(bp,dx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    } else if (instruction == 8) { //xor
                        if (reg == 11) { //ax
                            ax = Functions.xor(ax,dx);
                            for (int i = 16; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, ax.get(i-16));
                            }
                        } else if (reg == 21) { //bx
                            bx = Functions.xor(bx,dx);
                            for (int i = 16; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bx.get(i-16));
                            }
                        } else if (reg == 31) { //cx
                            cx = Functions.xor(cx,dx);
                            for (int i = 16; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cx.get(i-16));
                            }
                        } else if (reg == 41) { //dx
                            dx = Functions.xor(dx,dx);
                            for (int i = 16; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dx.get(i-16));
                            }
                        } else if (reg == 51) { //si
                            si = Functions.xor(si,dx);
                            for (int i = 16; i < Registers.esi.size(); i++) {
                                Registers.esi.set(i, si.get(i-16));
                            }
                        } else if (reg == 61) { //di
                            di = Functions.xor(di,dx);
                            for (int i = 16; i < Registers.edi.size(); i++) {
                                Registers.edi.set(i, di.get(i-16));
                            }
                        } else if (reg == 71) { //sp
                            sp = Functions.xor(sp,dx);
                            for (int i = 16; i < Registers.esp.size(); i++) {
                                Registers.esp.set(i, sp.get(i-16));
                            }
                        } else if (reg == 81) { //bp
                            bp = Functions.xor(bp,dx);
                            for (int i = 16; i < Registers.ebp.size(); i++) {
                                Registers.ebp.set(i, bp.get(i-16));
                            }
                        }
                    }
                }else if (source == -111) {//ah
                    if (instruction == 1) { //mov
                        if (reg == 111) { //ah
                            ah = Functions.mov(ah,value);
                            for (int i = 16; i < Registers.eax.size()-8; i++) {
                                Registers.eax.set(i, ah.get(i-16));
                            }
                        } else if (reg == 112) { //al
                            al = Functions.mov(al,value);
                            for (int i = 24; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, al.get(i-24));
                            }
                        } else if (reg == 211) { //bh
                            ArrayList<Integer> bh = new ArrayList<>();
                            for (int i = 16; i < Registers.ebx.size()-8; i++) {
                                bh.add(Registers.ebx.get(i));
                            }
                            bh = Functions.mov(bh,value);
                            for (int i = 16; i < Registers.ebx.size()-8; i++) {
                                Registers.ebx.set(i, bh.get(i-16));
                            }
                        } else if (reg == 212) { //bl
                            ArrayList<Integer> bl = new ArrayList<>();
                            for (int i = 24; i < Registers.ebx.size(); i++) {
                                bl.add(Registers.ebx.get(i));
                            }
                            bl = Functions.mov(bl,value);
                            for (int i = 24; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bl.get(i-24));
                            }
                        } else if (reg == 311) { //ch
                            ArrayList<Integer> ch = new ArrayList<>();
                            for (int i = 16; i < Registers.ecx.size()-8; i++) {
                                ch.add(Registers.ecx.get(i));
                            }
                            ch = Functions.mov(ch,value);
                            for (int i = 16; i < Registers.ecx.size()-8; i++) {
                                Registers.ecx.set(i, ch.get(i-16));
                            }
                        } else if (reg == 312) { //cl
                            ArrayList<Integer> cl = new ArrayList<>();
                            for (int i = 24; i < Registers.ecx.size(); i++) {
                                cl.add(Registers.ecx.get(i));
                            }
                            cl = Functions.mov(cl,value);
                            for (int i = 24; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cl.get(i-24));
                            }
                        } else if (reg == 411) { //dh
                            ArrayList<Integer> dh = new ArrayList<>();
                            for (int i = 16; i < Registers.edx.size()-8; i++) {
                                dh.add(Registers.ebx.get(i));
                            }
                            dh = Functions.mov(dh,value);
                            for (int i = 16; i < Registers.edx.size()-8; i++) {
                                Registers.edx.set(i, dh.get(i-16));
                            }
                        } else if (reg == 412) { //dl
                            ArrayList<Integer> dl = new ArrayList<>();
                            for (int i = 24; i < Registers.edx.size(); i++) {
                                dl.add(Registers.ebx.get(i));
                            }
                            dl = Functions.mov(dl,value);
                            for (int i = 24; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dl.get(i-24));
                            }
                        }
                    } else if (instruction == 2) { //add
                        if (reg == 111) { //ah

                            ah = Functions.add(ah,value);
                            for (int i = 16; i < Registers.eax.size()-8; i++) {
                                Registers.eax.set(i, ah.get(i-16));
                            }
                        } else if (reg == 112) { //al

                            al = Functions.add(al,value);
                            for (int i = 24; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, al.get(i-24));
                            }
                        } else if (reg == 211) { //bh
                            ArrayList<Integer> bh = new ArrayList<>();
                            for (int i = 16; i < Registers.ebx.size()-8; i++) {
                                bh.add(Registers.ebx.get(i));
                            }
                            bh = Functions.add(bh,value);
                            for (int i = 16; i < Registers.ebx.size()-8; i++) {
                                Registers.ebx.set(i, bh.get(i-16));
                            }
                        } else if (reg == 212) { //bl
                            ArrayList<Integer> bl = new ArrayList<>();
                            for (int i = 24; i < Registers.ebx.size(); i++) {
                                bl.add(Registers.ebx.get(i));
                            }
                            bl = Functions.add(bl,value);
                            for (int i = 24; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bl.get(i-24));
                            }
                        } else if (reg == 311) { //ch
                            ArrayList<Integer> ch = new ArrayList<>();
                            for (int i = 16; i < Registers.ecx.size()-8; i++) {
                                ch.add(Registers.ecx.get(i));
                            }
                            ch = Functions.add(ch,value);
                            for (int i = 16; i < Registers.ecx.size()-8; i++) {
                                Registers.ecx.set(i, ch.get(i-16));
                            }
                        } else if (reg == 312) { //cl
                            ArrayList<Integer> cl = new ArrayList<>();
                            for (int i = 24; i < Registers.ecx.size(); i++) {
                                cl.add(Registers.ecx.get(i));
                            }
                            cl = Functions.add(cl,value);
                            for (int i = 24; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cl.get(i-24));
                            }
                        } else if (reg == 411) { //dh
                            ArrayList<Integer> dh = new ArrayList<>();
                            for (int i = 16; i < Registers.edx.size()-8; i++) {
                                dh.add(Registers.ebx.get(i));
                            }
                            dh = Functions.add(dh,value);
                            for (int i = 16; i < Registers.edx.size()-8; i++) {
                                Registers.edx.set(i, dh.get(i-16));
                            }
                        } else if (reg == 412) { //dl
                            ArrayList<Integer> dl = new ArrayList<>();
                            for (int i = 24; i < Registers.edx.size(); i++) {
                                dl.add(Registers.ebx.get(i));
                            }
                            dl = Functions.add(dl,value);
                            for (int i = 24; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dl.get(i-24));
                            }
                        }

                    } else if (instruction == 3) { //sub
                        if (reg == 111) { //ah

                            ah = Functions.sub(ah,value);
                            for (int i = 16; i < Registers.eax.size()-8; i++) {
                                Registers.eax.set(i, ah.get(i-16));
                            }
                        } else if (reg == 112) { //al

                            al = Functions.sub(al,value);
                            for (int i = 24; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, al.get(i-24));
                            }
                        } else if (reg == 211) { //bh
                            ArrayList<Integer> bh = new ArrayList<>();
                            for (int i = 16; i < Registers.ebx.size()-8; i++) {
                                bh.add(Registers.ebx.get(i));
                            }
                            bh = Functions.sub(bh,value);
                            for (int i = 16; i < Registers.ebx.size()-8; i++) {
                                Registers.ebx.set(i, bh.get(i-16));
                            }
                        } else if (reg == 212) { //bl
                            ArrayList<Integer> bl = new ArrayList<>();
                            for (int i = 24; i < Registers.ebx.size(); i++) {
                                bl.add(Registers.ebx.get(i));
                            }
                            bl = Functions.sub(bl,value);
                            for (int i = 24; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bl.get(i-24));
                            }
                        } else if (reg == 311) { //ch
                            ArrayList<Integer> ch = new ArrayList<>();
                            for (int i = 16; i < Registers.ecx.size()-8; i++) {
                                ch.add(Registers.ecx.get(i));
                            }
                            ch = Functions.sub(ch,value);
                            for (int i = 16; i < Registers.ecx.size()-8; i++) {
                                Registers.ecx.set(i, ch.get(i-16));
                            }
                        } else if (reg == 312) { //cl
                            ArrayList<Integer> cl = new ArrayList<>();
                            for (int i = 24; i < Registers.ecx.size(); i++) {
                                cl.add(Registers.ecx.get(i));
                            }
                            cl = Functions.sub(cl,value);
                            for (int i = 24; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cl.get(i-24));
                            }
                        } else if (reg == 411) { //dh
                            ArrayList<Integer> dh = new ArrayList<>();
                            for (int i = 16; i < Registers.edx.size()-8; i++) {
                                dh.add(Registers.ebx.get(i));
                            }
                            dh = Functions.sub(dh,value);
                            for (int i = 16; i < Registers.edx.size()-8; i++) {
                                Registers.edx.set(i, dh.get(i-16));
                            }
                        } else if (reg == 412) { //dl
                            ArrayList<Integer> dl = new ArrayList<>();
                            for (int i = 24; i < Registers.edx.size(); i++) {
                                dl.add(Registers.ebx.get(i));
                            }
                            dl = Functions.sub(dl,value);
                            for (int i = 24; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dl.get(i-24));
                            }
                        }
                    } else if (instruction == 6) { //
                        if (reg == 111) { //ah
                            ah = Functions.and(ah,value);
                            for (int i = 16; i < Registers.eax.size()-8; i++) {
                                Registers.eax.set(i, ah.get(i-16));
                            }
                        } else if (reg == 112) { //al

                            al = Functions.and(al,value);
                            for (int i = 24; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, al.get(i-24));
                            }
                        } else if (reg == 211) { //bh
                            ArrayList<Integer> bh = new ArrayList<>();
                            for (int i = 16; i < Registers.ebx.size()-8; i++) {
                                bh.add(Registers.ebx.get(i));
                            }
                            bh = Functions.and(bh,value);
                            for (int i = 16; i < Registers.ebx.size()-8; i++) {
                                Registers.ebx.set(i, bh.get(i-16));
                            }
                        } else if (reg == 212) { //bl
                            ArrayList<Integer> bl = new ArrayList<>();
                            for (int i = 24; i < Registers.ebx.size(); i++) {
                                bl.add(Registers.ebx.get(i));
                            }
                            bl = Functions.and(bl,value);
                            for (int i = 24; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bl.get(i-24));
                            }
                        } else if (reg == 311) { //ch
                            ArrayList<Integer> ch = new ArrayList<>();
                            for (int i = 16; i < Registers.ecx.size()-8; i++) {
                                ch.add(Registers.ecx.get(i));
                            }
                            ch = Functions.and(ch,value);
                            for (int i = 16; i < Registers.ecx.size()-8; i++) {
                                Registers.ecx.set(i, ch.get(i-16));
                            }
                        } else if (reg == 312) { //cl
                            ArrayList<Integer> cl = new ArrayList<>();
                            for (int i = 24; i < Registers.ecx.size(); i++) {
                                cl.add(Registers.ecx.get(i));
                            }
                            cl = Functions.and(cl,value);
                            for (int i = 24; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cl.get(i-24));
                            }
                        } else if (reg == 411) { //dh
                            ArrayList<Integer> dh = new ArrayList<>();
                            for (int i = 16; i < Registers.edx.size()-8; i++) {
                                dh.add(Registers.ebx.get(i));
                            }
                            dh = Functions.and(dh,value);
                            for (int i = 16; i < Registers.edx.size()-8; i++) {
                                Registers.edx.set(i, dh.get(i-16));
                            }
                        } else if (reg == 412) { //dl
                            ArrayList<Integer> dl = new ArrayList<>();
                            for (int i = 24; i < Registers.edx.size(); i++) {
                                dl.add(Registers.ebx.get(i));
                            }
                            dl = Functions.and(dl,value);
                            for (int i = 24; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dl.get(i-24));
                            }
                        }
                    } else if (instruction == 7) { //or
                        if (reg == 111) { //ah
                            ah = Functions.or(ah,value);
                            for (int i = 16; i < Registers.eax.size()-8; i++) {
                                Registers.eax.set(i, ah.get(i-16));
                            }
                        } else if (reg == 112) { //al

                            al = Functions.or(al,value);
                            for (int i = 24; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, al.get(i-24));
                            }
                        } else if (reg == 211) { //bh
                            ArrayList<Integer> bh = new ArrayList<>();
                            for (int i = 16; i < Registers.ebx.size()-8; i++) {
                                bh.add(Registers.ebx.get(i));
                            }
                            bh = Functions.or(bh,value);
                            for (int i = 16; i < Registers.ebx.size()-8; i++) {
                                Registers.ebx.set(i, bh.get(i-16));
                            }
                        } else if (reg == 212) { //bl
                            ArrayList<Integer> bl = new ArrayList<>();
                            for (int i = 24; i < Registers.ebx.size(); i++) {
                                bl.add(Registers.ebx.get(i));
                            }
                            bl = Functions.or(bl,value);
                            for (int i = 24; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bl.get(i-24));
                            }
                        } else if (reg == 311) { //ch
                            ArrayList<Integer> ch = new ArrayList<>();
                            for (int i = 16; i < Registers.ecx.size()-8; i++) {
                                ch.add(Registers.ecx.get(i));
                            }
                            ch = Functions.or(ch,value);
                            for (int i = 16; i < Registers.ecx.size()-8; i++) {
                                Registers.ecx.set(i, ch.get(i-16));
                            }
                        } else if (reg == 312) { //cl
                            ArrayList<Integer> cl = new ArrayList<>();
                            for (int i = 24; i < Registers.ecx.size(); i++) {
                                cl.add(Registers.ecx.get(i));
                            }
                            cl = Functions.or(cl,value);
                            for (int i = 24; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cl.get(i-24));
                            }
                        } else if (reg == 411) { //dh
                            ArrayList<Integer> dh = new ArrayList<>();
                            for (int i = 16; i < Registers.edx.size()-8; i++) {
                                dh.add(Registers.ebx.get(i));
                            }
                            dh = Functions.or(dh,value);
                            for (int i = 16; i < Registers.edx.size()-8; i++) {
                                Registers.edx.set(i, dh.get(i-16));
                            }
                        } else if (reg == 412) { //dl
                            ArrayList<Integer> dl = new ArrayList<>();
                            for (int i = 24; i < Registers.edx.size(); i++) {
                                dl.add(Registers.ebx.get(i));
                            }
                            dl = Functions.or(dl,value);
                            for (int i = 24; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dl.get(i-24));
                            }
                        }
                    } else if (instruction == 8) { //xor
                        if (reg == 111) { //ah
                            ah = Functions.xor(ah,value);
                            for (int i = 16; i < Registers.eax.size()-8; i++) {
                                Registers.eax.set(i, ah.get(i-16));
                            }
                        } else if (reg == 112) { //al
                            al = Functions.xor(al,value);
                            for (int i = 24; i < Registers.eax.size(); i++) {
                                Registers.eax.set(i, al.get(i-24));
                            }
                        } else if (reg == 211) { //bh
                            ArrayList<Integer> bh = new ArrayList<>();
                            for (int i = 16; i < Registers.ebx.size()-8; i++) {
                                bh.add(Registers.ebx.get(i));
                            }
                            bh = Functions.xor(bh,value);
                            for (int i = 16; i < Registers.ebx.size()-8; i++) {
                                Registers.ebx.set(i, bh.get(i-16));
                            }
                        } else if (reg == 212) { //bl
                            ArrayList<Integer> bl = new ArrayList<>();
                            for (int i = 24; i < Registers.ebx.size(); i++) {
                                bl.add(Registers.ebx.get(i));
                            }
                            bl = Functions.xor(bl,value);
                            for (int i = 24; i < Registers.ebx.size(); i++) {
                                Registers.ebx.set(i, bl.get(i-24));
                            }
                        } else if (reg == 311) { //ch
                            ArrayList<Integer> ch = new ArrayList<>();
                            for (int i = 16; i < Registers.ecx.size()-8; i++) {
                                ch.add(Registers.ecx.get(i));
                            }
                            ch = Functions.xor(ch,value);
                            for (int i = 16; i < Registers.ecx.size()-8; i++) {
                                Registers.ecx.set(i, ch.get(i-16));
                            }
                        } else if (reg == 312) { //cl
                            ArrayList<Integer> cl = new ArrayList<>();
                            for (int i = 24; i < Registers.ecx.size(); i++) {
                                cl.add(Registers.ecx.get(i));
                            }
                            cl = Functions.xor(cl,value);
                            for (int i = 24; i < Registers.ecx.size(); i++) {
                                Registers.ecx.set(i, cl.get(i-24));
                            }
                        } else if (reg == 411) { //dh
                            ArrayList<Integer> dh = new ArrayList<>();
                            for (int i = 16; i < Registers.edx.size()-8; i++) {
                                dh.add(Registers.ebx.get(i));
                            }
                            dh = Functions.xor(dh,value);
                            for (int i = 16; i < Registers.edx.size()-8; i++) {
                                Registers.edx.set(i, dh.get(i-16));
                            }
                        } else if (reg == 412) { //dl
                            ArrayList<Integer> dl = new ArrayList<>();
                            for (int i = 24; i < Registers.edx.size(); i++) {
                                dl.add(Registers.ebx.get(i));
                            }
                            dl = Functions.xor(dl,value);
                            for (int i = 24; i < Registers.edx.size(); i++) {
                                Registers.edx.set(i, dl.get(i-24));
                            }
                        }
                    }

                }else if (source == -112) {//al

                }else if (source == -211) {

                }else if (source == -212) {

                }else if (source == -311) {

                }else if (source == -312) {

                }else if (source == -411) {

                }else if (source == -412) {

                }
            }
        }
        printAll();
    }

    static void printAll() {
        System.out.println("EAX : " + Registers.eax);
        System.out.println("EBX : " + Registers.ebx);
        System.out.println("ECX : " + Registers.ecx);
        System.out.println("EDX : " + Registers.edx);
        System.out.println("ESI : " + Registers.esi);
        System.out.println("EDI : " + Registers.edi);
        System.out.println("ESP : " + Registers.esp);
        System.out.println("EBP : " + Registers.ebp);
        System.out.println("SF : " + Flags.SF + "\tZF : " + Flags.ZF + "\tCF : " + Flags.CF + "\tOF : " + Flags.OF
        + "\tPF : " + Flags.PF);

    }
}