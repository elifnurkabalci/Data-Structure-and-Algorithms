public class File extends FileSystemElement {

    public File(String name, FileSystemElement parent) {
        super(name, parent);
        /*
         * FileSystemElement's name and parent are assign to values with super
         * O(1)
         */
    }

    /**
     * @param prefix
     */
    @Override
    public void print(String prefix) {
        System.out.println(prefix + "File: " + getName());
        /*
         * overriden print method, directly File name
         * O(1)
         */
    }
}
