public class Jarvis {
    public static void main(String[] args) {
        String name = "Jarvis";
        String logo = "      **     **     *******   **      ** **  ********\n" +
                "     /**    ****   /**////** /**     /**/** **////// \n" +
                "     /**   **//**  /**   /** /**     /**/**/**       \n" +
                "     /**  **  //** /*******  //**    ** /**/*********\n" +
                "     /** **********/**///**   //**  **  /**////////**\n" +
                " **  /**/**//////**/**  //**   //****   /**       /**\n" +
                "//***** /**     /**/**   //**   //**    /** ******** \n" +
                " /////  //      // //     //     //     // ////////  ";
        System.out.println(logo);
        String line = "____________________________________________________________";
        String greeting = "Good day Sir! I'm ";
        String question = "How can I help you today Sir?";
        String signOff = "Good bye Sir!";

        System.out.println(line);
        System.out.println(greeting + name);
        System.out.println(question);
        System.out.println(line);
        System.out.println(signOff);
        System.out.println(line);
    }
}