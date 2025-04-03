package VirtualCommandPrompt;

import java.util.*;

public class TreePrinter {
    public static String printTree(Directory directory, String indent) {
        StringBuilder treeStructure = new StringBuilder();
        for (Map.Entry<String, Directory> entry : directory.getSubfolders().entrySet()) {
            String folderName = entry.getKey();
            treeStructure.append(indent).append("\u251c ").append(folderName).append("\n");
            treeStructure.append(printTree(entry.getValue(), indent + " "));
        }
        return treeStructure.toString();
    }
}
