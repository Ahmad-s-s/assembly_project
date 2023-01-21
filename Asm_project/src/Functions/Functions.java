package Functions;

import java.util.ArrayList;

public class Functions {
    static ArrayList<Integer> and (ArrayList<Integer> reg, String andStr) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < reg.size(); i++) {
            if (reg.get(i).toString().equals("1") && andStr.charAt(i) == '1') {
                res.add(1);
            }else {
                res.add(0);
            }
        }
        reg = res;
        return res;
    }

    static ArrayList<Integer> and (ArrayList<Integer> reg, ArrayList<Integer> reg2) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < reg.size(); i++) {
            if (reg.get(i) == 1 && reg2.get(i) == 1) {
                res.add(1);
            }else {
                res.add(0);
            }
        }
        reg = res;
        return res;
    }

    static ArrayList<Integer> xor (ArrayList<Integer> reg, String xorStr) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < reg.size(); i++) {
            if (reg.get(i).equals(xorStr.charAt(i) - '0')) {
                res.add(0);
            }else {
                res.add(1);
            }
        }
        reg = res;
        return res;
    }

    static ArrayList<Integer> xor (ArrayList<Integer> reg, ArrayList<Integer> reg2) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < reg.size(); i++) {
            if (reg.get(i).equals(reg2.get(i))) {
                res.add(0);
            }else {
                res.add(1);
            }
        }
        reg = res;
        return res;
    }


}
