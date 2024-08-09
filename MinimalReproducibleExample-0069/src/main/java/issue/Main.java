package issue;

public class Main {

    public static void main(String[] args) {
        pascal.taie.Main.main(
                "--options-file",
                "MinimalReproducibleExample-0069/configs/options.yml"
        );
        // equivalent to:
//        pascal.taie.Main.main(
//                "-pp",
//                "-cp", "MinimalReproducibleExample-0069/analyzed-programs",
//                "--output-dir", "MinimalReproducibleExample-0069/output",
//                "-m", "Main",
//                "-a", """
//                      pta=
//                      cs:ci;
//                      implicit-entries:true;
//                      distinguish-string-constants:null;
//                      reflection-inference:solar;
//                      taint-config:MinimalReproducibleExample-0069/taint-config.yml;
//                      """
//        );
    }

}
