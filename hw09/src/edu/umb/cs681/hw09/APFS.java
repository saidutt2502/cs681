package edu.umb.cs681.hw09;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class APFS extends FileSystem implements Runnable {
    private String ownerName;
    private LocalDateTime lastModified;
    private static APFS instance = null;
    public APFS(String ownerName) {
        this.ownerName = ownerName;
        this.lastModified = LocalDateTime.now();
    }

    public APFS getInstance() {
        if (instance == null) {
            instance = new APFS(ownerName);
        }
        return instance;
    }

    @Override
    protected FSElement createDefaultRoot() {
        return new ApfsDirectory(null, "root");
    }

    public ApfsDirectory getRootDir() {
        ApfsDirectory root = (ApfsDirectory) this.getRoot();
        return root;
    }

    public void setRootDir(ApfsDirectory root) {
        super.setRoot(root);
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public LocalDateTime getLastModified() {
        return this.lastModified;
    }

    public void run() {
        System.out.println("\nThread #" + Thread.currentThread().getId());
        System.out.println("Size of " + getRootDir().getName() + " dir: " + getRootDir().getTotalSize());
        LinkedList<ApfsElement> rootChildren = getRootDir().getChildren();
        for (ApfsElement child : rootChildren) {
            System.out.println("\n-->"+child.getName());
            LinkedList<ApfsElement> child1Children =child.getChildren();
            for (ApfsElement child1 : child1Children) {
                System.out.println("------>"+child1.getName());
                LinkedList<ApfsElement> child2Children =child1.getChildren();
                for (ApfsElement child2 : child2Children) {
                    System.out.println("------>"+child2.getName());
                }
            }
        }
    }

    public static void main(String args[]) {
        APFS apfs = new APFS("APFS_FileSystem");
        apfs.initFileSystem("LocalDisk", 10000);
        ApfsDirectory root = apfs.getRootDir();
        ApfsDirectory applications = new ApfsDirectory(root, "APPS");
        root.appendChild(applications);
        ApfsFile fs1, fs2;
        fs1 = new ApfsFile(applications, "fs1", 40);
        fs2 = new ApfsFile(applications, "fs2", 40);
        applications.appendChild(fs1);
        applications.appendChild(fs2);
        Thread t1 = new Thread(apfs);
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        ApfsDirectory home  = new ApfsDirectory(root, "home");
        root.appendChild((home));
        ApfsFile fs3, fs4;
        fs3 = new ApfsFile(home, "fs3", 200);
        fs4 = new ApfsFile(home, "fs4", 300);
        home.appendChild(fs3);
        home.appendChild(fs4);
        Thread t2 = new Thread(apfs);
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
