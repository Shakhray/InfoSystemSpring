package sh.viewers;

import org.xml.sax.SAXException;
import sh.controllers.Commands;
import sh.controllers.Controller;
import sh.exception.*;
import sh.patterns.StudentPattern;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;

public class Console {
    Controller controller;
    StudentPattern studPattern;
    BufferedReader reader;
    Viewer viewer;

    public void runConsole() {
        viewer.welcome();
        viewer.helpMessage();
        while (true) {

            viewer.comandLine();
            String[] arr = getArgsFromCommandLine();
            try {
                Commands key = Commands.valueOf(arr[0].toUpperCase());
                switch (key) {
                    case ADD:
                        add();
                        break;
                    case HELP:
                        help();
                        break;
                    case STUDENTS:
                        students();
                        break;
                    case GROUPS:
                        groups();
                        break;
                    case UPDATE:
                        update();
                        break;
                    case DELETE:
                        delete();
                        break;
                    case EXIT:
                        exit();
                    case FIND:
                        find(arr);
                        break;
                    case UNDO:
                        undo();
                        break;
                    case REMOVEGROUP:
                        removeGroup();
                        break;
                    case COPY:
                        copy();
                        break;
                    case RENAMEGROUP:
                        renameGroup();
                        break;
                }
            } catch (IllegalArgumentException e) {
                viewer.badCommand();
            } catch (BadXmlFileException | SAXException | ParserConfigurationException e) {
                viewer.xmlFileError();
            } catch (FormarOfStudentException e) {
                viewer.badStudentFormat();
            } catch (CopyNotSupportException e) {
                viewer.copyNotSupport();
            } catch (IOException e) {
                viewer.programError();
                e.printStackTrace();
            } catch (NothingToDeleteException e) {
                viewer.nothingToDelete();
            } catch (StudentNotFoundException e) {
                viewer.studentNotFound();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private String[] getArgsFromCommandLine() {
        String command = null;
        try {
            command = reader.readLine();
        } catch (IOException e) {
            viewer.programError();
            e.printStackTrace();
        }
        return command != null ? command.split(" ") : new String[0];
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setStudPattern(StudentPattern studPattern) {
        this.studPattern = studPattern;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }

    private void copy() throws ParserConfigurationException, CopyNotSupportException, IOException, BadXmlFileException, SAXException, NothingToDeleteException, ClassNotFoundException {
        controller.copyFile();
    }

    private void undo() {
        controller.undo();
    }

    private void exit() {
        System.exit(0);
    }

    private void groups() throws BadXmlFileException, ParserConfigurationException, SAXException, IOException {
        viewer.printCollection(controller.allGroups());
    }

    private void students() throws BadXmlFileException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException {
        viewer.printCollection(controller.allStudents());
    }

    private void help() {
        viewer.help();
    }

    private void find(String[] arr) throws BadXmlFileException, ParserConfigurationException, SAXException, IOException, ClassNotFoundException {
        String[] str = new String[arr.length - 1];
        System.arraycopy(arr, 1, str, 0, str.length);
        if (str.length > 0) {
            if (controller.find(str).isEmpty()) {
                viewer.notFound(str);
            } else {
                viewer.printCollection(controller.find(str));
            }
        } else {
            viewer.badCommand();
        }
    }

    private void add() throws IOException, NothingToDeleteException, BadXmlFileException, ParserConfigurationException, SAXException, CopyNotSupportException, FormarOfStudentException, ClassNotFoundException {
        String[] stud = new String[7];
        for (int i = 1; i <= 7; i++) {
            viewer.update(i);
            stud[i - 1] = reader.readLine();
        }
        if (studPattern.matcher(stud[0], stud[1], stud[2], stud[3], stud[4], stud[5], stud[6]))
            controller.add(stud);
        else throw new FormarOfStudentException();
    }

    private void update() throws Exception {
        String[] stud = new String[7];
        viewer.update(0);
        int index = Integer.valueOf(reader.readLine());
        for (int i = 0; i < 7; i++) {
            viewer.update(i + 1);
            stud[i] = reader.readLine();
        }
        if (("".equals(stud[0]) || studPattern.wordMatcher(stud[0])) && ("".equals(stud[1]) || studPattern.wordMatcher(stud[1])) &&
                ("".equals(stud[2]) || studPattern.wordMatcher(stud[2])) && ("".equals(stud[3]) || studPattern.wordMatcher(stud[3])) &&
                ("".equals(stud[4]) || studPattern.numberMatcher(stud[4])) && ("".equals(stud[5]) || studPattern.idMatcher(stud[5])) &&
                ("".equals(stud[6]) || studPattern.dateMatcher(stud[6])))
            controller.update(stud, index - 1);
        else throw new FormarOfStudentException();
    }

    private void delete() throws IOException, NothingToDeleteException, BadXmlFileException, ParserConfigurationException, SAXException, CopyNotSupportException, ClassNotFoundException {
        viewer.delete();
        int index = Integer.valueOf(reader.readLine());
        controller.delete(index - 1);
    }

    private void removeGroup() throws IOException, NothingToDeleteException, BadXmlFileException, ParserConfigurationException, SAXException, CopyNotSupportException, FormarOfStudentException {
        viewer.changeGroup(0);
        String faculty = reader.readLine();
        viewer.changeGroup(1);
        String number = reader.readLine();
        if (studPattern.wordMatcher(faculty) && studPattern.numberMatcher(number))
            controller.deleteGroup(number, faculty);
        else throw new FormarOfStudentException();
    }

    private void renameGroup() throws IOException, NothingToDeleteException, BadXmlFileException, ParserConfigurationException, SAXException, CopyNotSupportException, FormarOfStudentException {
        viewer.changeGroup(0);
        String faculty = reader.readLine();
        viewer.changeGroup(1);
        String number = reader.readLine();
        viewer.changeGroup(2);
        String renamedgroup = reader.readLine();
        if (studPattern.wordMatcher(faculty) && studPattern.numberMatcher(number))
            controller.renameGroup(faculty, number, renamedgroup);
        else throw new FormarOfStudentException();
    }
}
