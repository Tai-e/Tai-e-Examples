package cg;

public class Main {

    public static void main(String[] args) {
        pascal.taie.Main.main(
                "-pp",
                "--class-path", "CallGraph/analyzed-programs",
                "--output-dir", "CallGraph/output",
                "-m", "Interface3",
                "-a", "pta=implicit-entries:false;only-app:true;dump:true;",
                "-a", "cg=dump:true;dump-methods:true;dump-call-edges:true;"
        );
    }

}
