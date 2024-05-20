import java.sql.Timestamp;

public abstract class FileSystemElement {
    protected String name;
    protected Timestamp dateCreated;
    protected FileSystemElement parent;

    public FileSystemElement(String name, FileSystemElement parent) {
        /*
         * constructor of filesystemelement
         * O(1)
         * assign only initial declarations
         */
        this.name = name;
        this.parent = parent;
        this.dateCreated = new Timestamp(System.currentTimeMillis());
    }

    /**
     * @return String
     */
    public String getName() {
        /*
         * getter
         * O(1)
         */
        return name;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getDataCreated() {
        /*
         * getter
         * O(1)
         */
        return dateCreated;
    }

    /**
     * @return FileSystemElement
     */
    public FileSystemElement getParent() {
        /*
         * getter
         * O(1)
         */
        return parent;
    }

    /**
     * @param parent
     */
    public void setParent(FileSystemElement parent) {
        this.parent = parent;
        /*
         * is used for move element part
         * O(1)
         */
    }

    public abstract void print(String prefix); /* to help printing directory */

}
