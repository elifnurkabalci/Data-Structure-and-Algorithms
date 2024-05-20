import java.util.Collections;
import java.util.Comparator;

public class FileSystem {
    private Directory root;

    public FileSystem() {
        /*
         * Consturaction of filesystem
         * First definiton of root of tree
         * Root's parent is null
         */
        this.root = new Directory("root", null);
    }

    /**
     * @param name
     * @param parent
     */
    public void createFile(String name, Directory parent) {
        /*
         * if there isnt exist element add this
         * Time complexity O(1)
         */
        File f = new File(name, parent);
        if (parent.getChildren().indexOf(f) == -1)
            parent.addElement(f);

    }

    /**
     * @param name
     * @param parent
     */
    public void createDirectory(String name, Directory parent) {
        /*
         * If new directory isnt exist in linkedlist add
         * O(1)
         */
        Directory d = new Directory(name, parent);
        if (parent.getChildren().indexOf(d) == -1)
            parent.addElement(d);
    }

    /**
     * @param name
     * @param parent
     */
    public void deleteFile(String name, Directory parent) {
        /*
         * O(n) with for
         */
        for (int i = 0; i < parent.getChildren().size(); i++) { /* search all children */
            if (parent.getChildren().get(i) instanceof Directory) /* check child type is Directory or not */
                deleteFile(name, (Directory) parent.getChildren().get(i)); /* Recursion calling with child */

            else if (parent.getChildren().get(i) instanceof File) { /* if element is File */
                File file = (File) parent.getChildren().get(i);
                if (file.getName().equals(name)) { /* element is file and this name is equal with name, remove it */
                    parent.removeElement(file);
                    return; /* ending of the method */
                }
            }
        }
        // System.err.println("There is no file in that location");
    }

    /**
     * @param name
     * @param parent
     */
    public void deleteDirectory(String name, Directory parent) {
        /*
         * O(n) for and calling recursion
         */
        if (parent.getName().equals(name)) { /* If parents name is equl with name, remove it */
            ((Directory) parent.getParent()).removeElement(parent);
            return;
        }
        for (int i = 0; i < parent.getChildren().size(); i++) {
            if (parent.getChildren().get(i) instanceof Directory) /* search the directories to find */
                deleteDirectory(name, (Directory) parent.getChildren().get(i));
        }
        // System.err.println("There is no directory in that location");
    }

    /**
     * @param name
     * @param newParent
     */
    public void moveelement(String name, Directory newParent) {
        /*
         * O(1)
         */
        /* use search for delete last parent's child */
        FileSystemElement child = search(name, root);
        ((Directory) child.getParent()).removeElement((FileSystemElement) child);
        /* searching for finding the last element */
        child.setParent(newParent);
        /* new element's parent is assigned to newparent */
        newParent.addElement(child);
        /* add element to newparent's child */
    }

    /**
     * @param path
     * @return FileSystemElement
     */
    public FileSystemElement found(String path) {
        /*
         * nested loop - O(n^2)
         */
        String[] arr = path.split("/"); /* path's spliting */
        Directory temp = root;
        /* arr's first element is become root, so i become from 1 */
        for (int i = 1; i < arr.length; i++) {
            int index = -1;
            for (int j = 0; j < temp.getChildren().size(); j++) {
                if (temp.getChildren().get(j).getName().equals(arr[i])) { /* if found */
                    index = j;
                    return temp.getChildren().get(j);
                }
            }
            if (index == -1) {
                return null; /* there is no element like that */
            }
            temp = (Directory) temp.getChildren().get(index);
        }
        return temp;

    }

    /**
     * @param name
     * @param dir
     * @return FileSystemElement
     */
    public FileSystemElement search(String name, Directory dir) {
        /*
         * Recursion
         * for - O(n)
         */
        for (FileSystemElement child : dir.getChildren()) { /* search all children from dir */
            if (child.getName().equals(name)) { /* if its found, return directory element */
                return child;
            }
            if (child instanceof Directory) { /* seraching recursion area */
                FileSystemElement result = search(name, (Directory) child);
                if (result != null) {
                    return result;
                }
            }
        }
        return null; /* there is no element like that */
    }

    /**
     * @param dir
     * @param space
     */
    public void printDirectoryTree(Directory dir, String space) {
        /*
         * for - O(n)
         * Recursion
         */
        /* print entire tree strt from root */
        for (FileSystemElement child : dir.getChildren()) {
            if (child == null) { /* null means root's parent, last of the looping */
                return;
            }
            if (child instanceof Directory) { /* print directories */
                System.out.println(space + "* " + child.getName());

                printDirectoryTree((Directory) child, (space + "    "));
            } else { /* print files */
                System.out.println(space + "- " + child.getName());
            }
        }

    }

    /**
     * @param dir
     */
    public void listContent(Directory dir) {
        /* list all content in specified directory */
        if (dir == null)
            return;

        dir.print("*"); /* call directory print method */
    }

    /**
     * @param dir
     */
    public void sortDirectoryByDate(Directory dir) {
        /* sort all content in dir */
        /*
         * O(n)
         */
        Collections.sort(dir.getChildren(), Comparator.comparing(FileSystemElement::getDataCreated));
        for (int i = 0; i < dir.getChildren().size(); i++) { /* printing children name and data created time */
            System.out.println("Name: " + dir.getChildren().get(i).getName() +
                    "Date: " + dir.getChildren().get(i).getDataCreated());
        }
    }

    /**
     * @param dir
     * @return String
     */
    public String getCurrentPath(Directory dir) {
        /*
         * Recursion
         * String builder for declaration of current path
         */
        if (dir == null)
            return "";

        return "/root" + getCurrentPath((Directory) dir.parent);
    }

    /**
     * @param path
     * @return Directory
     */
    public Directory changeDirectory(String path) {
        /*
         * If it is exist, if it is directory
         * Change directory
         * O(1)
         */
        FileSystemElement foundElement = found(path);
        if (foundElement == null || !(foundElement instanceof Directory)) {
            System.err.println("Path cannot be found, Returning to root");
            return root;
        }
        return (Directory) foundElement;
    }

    /**
     * @return Directory
     */
    public Directory getRoot() {
        /*
         * Getter
         * O(1)
         */
        return root;
    }
}
