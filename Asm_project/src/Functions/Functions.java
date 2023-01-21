package Functions;

import java.util.ArrayList;

import Variables.Flags;
import Variables.Flags.*;
import com.sun.org.apache.xpath.internal.operations.Variable;


//all little-endian
public class Functions {

    public static int lsToUSBinary(ArrayList<Integer> reg) {
        int res = 0;
        int power = 1;
        for (Integer integer : reg) {
            res += integer * power;
            power *= 2;
        }
        return res;
    }

    public static int lsToSBinary(ArrayList<Integer> reg) {
        ArrayList<Integer> newReg = new ArrayList<>();
        if (reg.get(reg.size() - 1) == 0) {
            return lsToUSBinary(reg);
        } else {
            for (Integer integer : reg) {
                if (integer == 1) {
                    newReg.add(0);
                } else {
                    newReg.add(1);
                }
            }
            StringBuilder builder = new StringBuilder();
            builder.append(1);
            for (int i = 0; i < newReg.size() - 1; i++) {
                builder.append(0);
            }
            newReg = add(newReg, builder.toString());
            int t = lsToUSBinary(newReg);
            return -1 * t;
        }
    }

    public static ArrayList<Integer> stringToList(String str) {
        ArrayList<Integer> ls = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            int c = str.charAt(i) - '0';
            ls.add(c);
        }
        return ls;
    }


    public static void setZF(ArrayList<Integer> reg) {
        Flags.ZF = 1;
        for (int i = 0; i < reg.size(); i++) {
            if (reg.get(i) == 1) {
                Flags.ZF = 0;
                break;
            }
        }
    }

    public static void setSF(ArrayList<Integer> reg) {
        Flags.SF = reg.get(reg.size() - 1);
    }

    public static void setPF(ArrayList<Integer> reg) {
        int t = 0;
        for (int i = 0; i < reg.size(); i++) {
            if (reg.get(i) == 1) {
                t += 1;
            }
        }
        if (t % 2 == 0) {
            Flags.PF = 1;
        }
    }


    public static ArrayList<Integer> and(ArrayList<Integer> reg, String andStr) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < reg.size(); i++) {
            if (reg.get(i).toString().equals("1") && andStr.charAt(i) == '1') {
                res.add(1);
            } else {
                res.add(0);
            }
        }
        reg = res;
        Flags.OF = 0;
        Flags.CF = 0;
        setPF(res);
        setSF(res);
        setZF(res);

        return res;
    }

    public static ArrayList<Integer> and(ArrayList<Integer> reg, ArrayList<Integer> reg2) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < reg.size(); i++) {
            if (reg.get(i) == 1 && reg2.get(i) == 1) {
                res.add(1);
            } else {
                res.add(0);
            }
        }
        reg = res;
        Flags.OF = 0;
        Flags.CF = 0;
        setPF(res);
        setSF(res);
        setZF(res);
        return res;
    }

    public static ArrayList<Integer> xor(ArrayList<Integer> reg, String xorStr) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < reg.size(); i++) {
            if (reg.get(i).equals(xorStr.charAt(i) - '0')) {
                res.add(0);
            } else {
                res.add(1);
            }
        }
        reg = res;
        Flags.OF = 0;
        Flags.CF = 0;
        setPF(res);
        setSF(res);
        setZF(res);
        return res;
    }

    public static ArrayList<Integer> xor(ArrayList<Integer> reg, ArrayList<Integer> reg2) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < reg.size(); i++) {
            if (reg.get(i).equals(reg2.get(i))) {
                res.add(0);
            } else {
                res.add(1);
            }
        }
        reg = res;
        Flags.OF = 0;
        Flags.CF = 0;
        setPF(res);
        setSF(res);
        setZF(res);
        return res;
    }

    public static ArrayList<Integer> or(ArrayList<Integer> reg, String andStr) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < reg.size(); i++) {
            if (reg.get(i).toString().equals("0") && andStr.charAt(i) == '0') {
                res.add(0);
            } else {
                res.add(1);
            }
        }
        reg = res;
        Flags.OF = 0;
        Flags.CF = 0;
        setPF(res);
        setSF(res);
        setZF(res);
        return res;
    }

    public static ArrayList<Integer> or(ArrayList<Integer> reg, ArrayList<Integer> reg2) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < reg.size(); i++) {
            if (reg.get(i) == 0 && reg2.get(i) == 0) {
                res.add(0);
            } else {
                res.add(1);
            }
        }
        reg = res;
        Flags.OF = 0;
        Flags.CF = 0;
        setPF(res);
        setSF(res);
        setZF(res);
        return res;
    }

    public static ArrayList<Integer> not(ArrayList<Integer> reg) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < reg.size(); i++) {
            if (reg.get(i).equals(0)) {
                res.add(1);
            } else {
                res.add(0);
            }
        }
        reg = res;
        return res;
    }

    public static ArrayList<Integer> add(ArrayList<Integer> reg, ArrayList<Integer> reg2) {
        ArrayList<Integer> res = new ArrayList<>();
        int cin = 0;
        Integer step = 0;
        for (int i = 0; i < reg.size(); i++) {
            step = 0;
            step += reg.get(i);
            step += reg2.get(i);
            step += cin;
            if (step == 3) {
                res.add(1);
                cin = 1;
                continue;
            } else if (step == 2) {
                res.add(0);
                cin = 1;
                continue;
            } else if (step == 1) {
                res.add(1);
                cin = 0;
                continue;
            } else {
                res.add(0);
                cin = 0;
                continue;
            }
        }
        reg = res;
        Flags.CF = cin;
        setPF(res);
        setSF(res);
        setZF(res);
        if (reg.get(reg.size() - 1) == 1 && reg2.get(reg2.size() - 1) == 1 && res.get(res.size() - 1) == 0) {
            Flags.OF = 1;
        } else if (reg.get(reg.size() - 1) == 0 && reg2.get(reg2.size() - 1) == 0 && res.get(res.size() - 1) == 1) {
            Flags.OF = 1;
        } else {
            Flags.OF = 0;
        }
        return res;
    }

    public static ArrayList<Integer> add(ArrayList<Integer> reg, String strAdd) {
        ArrayList<Integer> res = new ArrayList<>();
        int cin = 0;
        Integer step = 0;
        for (int i = 0; i < reg.size(); i++) {
            step = 0;
            step += reg.get(i);
            step += (strAdd.charAt(i) - '0'); //?
            step += cin;
            if (step == 3) {
                res.add(1);
                cin = 1;
                continue;
            } else if (step == 2) {
                res.add(0);
                cin = 1;
                continue;
            } else if (step == 1) {
                res.add(1);
                cin = 0;
                continue;
            } else {
                res.add(0);
                cin = 0;
                continue;
            }
        }
        reg = res;
        Flags.CF = cin;
        setPF(res);
        setSF(res);
        setZF(res);
        if (reg.get(reg.size() - 1) == 1 && strAdd.charAt(strAdd.length() - 1) == 1 && res.get(res.size() - 1) == 0) {
            Flags.OF = 1;
        } else if (reg.get(reg.size() - 1) == 0 && strAdd.charAt(strAdd.length() - 1) == 0 && res.get(res.size() - 1) == 1) {
            Flags.OF = 1;
        } else {
            Flags.OF = 0;
        }
        return res;
    }

    public static ArrayList<Integer> sub(ArrayList<Integer> reg, ArrayList<Integer> reg2) {
        ArrayList<Integer> res = new ArrayList<>();


        ArrayList<Integer> newReg = new ArrayList<>();
        for (Integer integer : reg) {
            if (integer == 1) {
                newReg.add(0);
            } else {
                newReg.add(1);
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append(1);
        for (int i = 0; i < newReg.size() - 1; i++) {
            builder.append(0);
        }
        newReg = add(newReg, builder.toString());
        res = add(reg, newReg);


        if (lsToUSBinary(reg) < lsToUSBinary(reg2)) {
            Flags.CF = 1;
        } else {
            Flags.CF = 0;
        }
        setZF(res);
        setPF(res);
        setSF(res);
        if (reg.get(reg.size() - 1) == 1 && reg2.get(reg2.size() - 1) == 1 && res.get(res.size() - 1) == 0) {
            Flags.OF = 1;
        } else if (reg.get(reg.size() - 1) == 0 && reg2.get(reg2.size() - 1) == 0 && res.get(res.size() - 1) == 1) {
            Flags.OF = 1;
        } else {
            Flags.OF = 0;
        }

        reg = res;
        return res;
    }

    public static ArrayList<Integer> sub(ArrayList<Integer> reg, String strSub) {
        ArrayList<Integer> newReg = new ArrayList<>();
        newReg = stringToList(strSub);
        return sub(reg, newReg);
    }

    public static ArrayList<Integer> inc(ArrayList<Integer> reg) {
        ArrayList<Integer> res = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        builder.append(1);
        for (int i = 0; i < reg.size() - 1; i++) {
            builder.append(0);
        }
        res = add(reg, builder.toString());
        reg = res;
        return res;
    }

    public static ArrayList<Integer> dec(ArrayList<Integer> reg) {
        ArrayList<Integer> res = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        builder.append(1);
        for (int i = 0; i < reg.size() - 1; i++) {
            builder.append(0);
        }
        res = sub(reg, builder.toString());
        reg = res;
        return res;
    }

}
