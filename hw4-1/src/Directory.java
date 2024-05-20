import java.util.LinkedList;

public class Directory extends FileSystemElement {
    private LinkedList<FileSystemElement> children;

    public Directory(String name, FileSystemElement parent) {
        /*
         * Constructor of Directory
         * O(1)
         * Initial declaration of children linkedlist
         * super is for FileSystemElement class
         */
        super(name, parent);
        children = new LinkedList<>();
    }

    /**
     * @param element
     */
    public void addElement(FileSystemElement element) {
        /*
         * element adding to linkedlist
         * O(1)
         */
        children.add(element);
    }

    /**
     * @param element
     */
    public void removeElement(FileSystemElement element) {
        /*
         * element removing to linkedlist
         * O(1)
         */
        children.remove(element);
    }

    /**
     * @return LinkedList<FileSystemElement>
     */
    public LinkedList<FileSystemElement> getChildren() {
        /*
         * getter for children linkedlist
         * O(1)
         */
        return children;
    }

    /**
     * @param prefix
     */
    @Override
    public void print(String prefix) {
        /*
         * overriden print method
         * print also children
         * 
         * O(n) time complexity
         */
        System.out.println(prefix + "Directory: " + getName());
        for (FileSystemElement elem : children)
            elem.print(prefix + " ");
    }

}
