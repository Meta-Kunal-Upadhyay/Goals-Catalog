package VirtualCommandPrompt;

import java.util.*;

public class DirectoryManager {
    private Directory root;
    private Directory currentDirectory;

    public DirectoryManager() {
        root = new Directory("R:");
        currentDirectory = root;
    }

    public String mkdir(String folderName) {
        if (currentDirectory.hasSubfolder(folderName)) {
            return "Error: Directory '" + folderName + "' already exists.";
        }
        Directory newDirectory = new Directory(folderName);
        currentDirectory.addSubfolder(newDirectory);
        return "Directory '" + folderName + "' created successfully.";
    }

    public String cd(String folderName) {
        if ("..".equals(folderName)) {
            if (currentDirectory != root) {
                currentDirectory = findParent(root, currentDirectory);
                return "Changed to parent directory " + currentDirectory.getName();
            } else {
                return "Error: Already in the root directory.";
            }
        }
        if (currentDirectory.hasSubfolder(folderName)) {
            currentDirectory = currentDirectory.getSubfolder(folderName);
            return "Changed directory to " + currentDirectory.getName() + ".";
        } else {
            return "Error: Directory '" + folderName + "' does not exist.";
        }
    }

    public String list() {
        Map<String, Directory> subfolders = currentDirectory.getSubfolders();
        if (subfolders.isEmpty()) {
            return "No subdirectories found.";
        }

        StringBuilder result = new StringBuilder("Listing subdirectories of " + currentDirectory.getName() + ":\n");
        for (Directory dir : subfolders.values()) {
            result.append(dir.getName()).append(" - Created: ").append(dir.getCreatedAt()).append("\n");
        }
        result.append("\nTotal subfolders: ").append(subfolders.size());
        return result.toString();
    }

    public String find(String folderName) {
        Directory foundDir = findRecursive(root, folderName);
        if (foundDir != null) {
            return "Folder '" + folderName + "' found in directory " + foundDir.getName() + ".";
        } else {
            return "Error: Folder '" + folderName + "' not found.";
        }
    }

    private Directory findRecursive(Directory currentDir, String folderName) {
        if (currentDir.hasSubfolder(folderName)) {
            return currentDir.getSubfolder(folderName);
        }

        for (Directory subfolder : currentDir.getSubfolders().values()) {
            Directory found = findRecursive(subfolder, folderName);
            if (found != null) return found;
        }
        return null;
    }

    public String tree() {
        return TreePrinter.printTree(root, "");
    }

    private Directory findParent(Directory currentDir, Directory childDir) {
        for (Directory subfolder : currentDir.getSubfolders().values()) {
            if (subfolder == childDir) return currentDir;
            Directory foundParent = findParent(subfolder, childDir);
            if (foundParent != null) return foundParent;
        }
        return null;
    }

    public Directory getCurrentDirectory() {
        return currentDirectory;
    }
}
