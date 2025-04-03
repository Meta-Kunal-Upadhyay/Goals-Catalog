package VirtualCommandPrompt;


import java.util.HashMap;
import java.util.Map;
import java.util.Date;

public class Directory {
    private String name;
    private Map<String, Directory> subfolders;
    private Date createdAt;

    public Directory(String name) {
        this.name = name;
        this.subfolders = new HashMap<>();
        this.createdAt = new Date();
    }

    public String getName() {
        return name;
    }

    public Map<String, Directory> getSubfolders() {
        return subfolders;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void addSubfolder(Directory directory) {
        subfolders.put(directory.getName(), directory);
    }

    public boolean hasSubfolder(String name) {
        return subfolders.containsKey(name);
    }

    public Directory getSubfolder(String name) {
        return subfolders.get(name);
    }
}
