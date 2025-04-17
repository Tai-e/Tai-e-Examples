package cep;

import pascal.taie.World;
import pascal.taie.analysis.graph.callgraph.CallGraph;
import pascal.taie.analysis.graph.callgraph.CallGraphBuilder;
import pascal.taie.analysis.graph.callgraph.Edge;
import pascal.taie.ir.stmt.Invoke;
import pascal.taie.language.classes.ClassHierarchy;
import pascal.taie.language.classes.JMethod;
import pascal.taie.util.collection.MultiMap;
import pascal.taie.util.collection.MultiMapCollector;

public class Main {

    public static void main(String[] args) {
        pascal.taie.Main.main(
                "-pp",
                "--class-path", "CustomizeEntryPoints/analyzed-programs",
                "--output-dir", "CustomizeEntryPoints/output",
                "--input-classes", "CustomEntryPoints",
                "-a", "pta=implicit-entries:false;only-app:true;dump:true;"
                        + "plugins:[cep.CustomEntryPointPlugin];",
                "-a", "cg=dump:true;dump-methods:true;dump-call-edges:true;"
        );
        // do assertions
        CallGraph<Invoke, JMethod> cg = World.get().getResult(CallGraphBuilder.ID);
        MultiMap<JMethod, JMethod> callerCalleeRelations = cg.edges().collect(
                MultiMapCollector.get(e -> e.getCallSite().getContainer(), Edge::getCallee));
        ClassHierarchy hierarchy = World.get().getClassHierarchy();
        for (String[] callerCalleeRelation : CALLER_CALLEE_RELATIONS) {
            boolean reachable = Boolean.parseBoolean(callerCalleeRelation[0]);
            JMethod caller = hierarchy.getMethod(callerCalleeRelation[1]);
            JMethod callee = hierarchy.getMethod(callerCalleeRelation[2]);
            if (reachable != callerCalleeRelations.contains(caller, callee)) {
                System.err.println("Caller: " + caller);
                System.err.println("Callee: " + callee);
                System.err.println("Expected: " + reachable);
                System.err.println("Actual: " + callerCalleeRelations.contains(caller, callee));
            }
        }
    }

    private static final String[][] CALLER_CALLEE_RELATIONS = {
            {
                    "false",
                    "<CustomEntryPoints: void entryWithEmptyParam(Param1,Param1[])>",
                    "<Param1: java.lang.String getS1()>",
            },
            {
                    "false",
                    "<CustomEntryPoints: void entryWithEmptyParam(Param1,Param1[])>",
                    "<Param1: void setS2(java.lang.String)>",
            },
            {
                    "true",
                    "<CustomEntryPoints: void entryWithDeclaredParam1(Param1,Param1[])>",
                    "<Param1: java.lang.String getS1()>",
            },
            {
                    "true",
                    "<CustomEntryPoints: void entryWithDeclaredParam1(Param1,Param1[])>",
                    "<Param1: void setS2(java.lang.String)>",
            },
            {
                    "true",
                    "<CustomEntryPoints: void entryWithDeclaredParam2(Param2)>",
                    "<Param2: java.lang.String getS1()>",
            },
            {
                    "true",
                    "<CustomEntryPoints: void entryWithDeclaredParam2(Param2)>",
                    "<Param2: Param1 getP1()>",
            },
            {
                    "true",
                    "<CustomEntryPoints: void entryWithDeclaredParam2(Param2)>",
                    "<Param1: java.lang.String getS2()>",
            },
            {
                    "true",
                    "<CustomEntryPoints: void entryWithSpecifiedParam(Param1,Param1[],java.lang.String)>",
                    "<Param1: java.lang.String getS1()>",
            },
            {
                    "true",
                    "<CustomEntryPoints: void entryWithSpecifiedParam(Param1,Param1[],java.lang.String)>",
                    "<Param1: void setS2(java.lang.String)>",
            },
            {
                    "true",
                    "<CustomEntryPoints: void entryWithSpecifiedParam(Param1,Param1[],java.lang.String)>",
                    "<java.lang.String: java.lang.String toString()>",
            },
            {
                    "true",
                    "<CustomEntryPoints: void entryWithSpecifiedParam(Param1,Param1[],java.lang.String)>",
                    "<java.lang.Object: java.lang.String toString()>",
            },
    };

}
