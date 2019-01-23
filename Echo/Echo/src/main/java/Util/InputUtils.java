package Util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class InputUtils {
    private static final BufferedReader KEYBOARD_INPUT = new BufferedReader(new InputStreamReader(System.in));
    private InputUtils() {}
    public static String getstring(String prompt){
        String returnData = null;//进行接收数据的返回
        boolean flag = true;
        while(flag){
            System.out.println(prompt);
            try{
                returnData = KEYBOARD_INPUT.readLine();
                if(returnData == null||"".equals(returnData)){
                    System.out.println("输入数据不允许为空！");
                }else {
                    flag = false;
                }

            }catch (Exception e){
                System.out.println("输入的数据错误！");
            }
        }
        return returnData;
//        System.out.println(prompt);
//        Scanner scan = new Scanner(System.in);
//        try {
//             data = scan.nextLine();
//            if (data == null || "".equalsIgnoreCase(data)) {
//                System.out.println("输入的数据不可为空！");
//            }
////            String s =
//        }catch (Exception e){
//            System.out.println("输入的数据错误");
//        }
//        return data;
    }
}
