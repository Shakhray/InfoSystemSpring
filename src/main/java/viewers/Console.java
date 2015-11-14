package viewers;

import controllers.Commands;
import controllers.Controller;
import exception.*;
import org.xml.sax.SAXException;
import patterns.StudentPattern;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;

public class Console {

    Controller controller;
    StudentPattern studPattern;
    BufferedReader reader;
    Viewer viewer;

    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }

    public void setStudpattern(StudentPattern studPattern) {
        this.studPattern = studPattern;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void runConsole() {
        viewer.welcome();
        viewer.xmlOrSerial();
//ololo
        while (true) {
            try {
                viewer.comandLine();
                String command = reader.readLine().toUpperCase();
                String databasekey = command;
                controller.useModel(databasekey);
                break;
            } catch (IllegalArgumentException e) {
                viewer.xmlOrSerialErrorCommand();
            } catch (ClassNotFoundException e) {
                viewer.programError();
                e.printStackTrace();
            } catch (IOException e) {
                viewer.programError();
                e.printStackTrace();
            }
        }
        viewer.helpMessage();
        while (true) {

            viewer.comandLine();
            String command = null;
            try {
                command = reader.readLine();
            } catch (IOException e) {
                viewer.programError();
                e.printStackTrace();
            }
            String[] arr = command.split(" ");
            try {
                Commands key = Commands.valueOf(arr[0].toUpperCase());
                switch (key) {
                    case ADD: {
                        String[] stud = new String[7];
                        for (int i = 1; i <= 7; i++) {
                            viewer.update(i);
                            stud[i - 1] = reader.readLine();
                        }
                        if (studPattern.matcher(stud[0], stud[1], stud[2], stud[3], stud[4], stud[5], stud[6]))
                            controller.add(stud);
                        else throw new FormarOfStudentException();
                        break;
                    }
                    case HELP: {
                        viewer.help();
                        break;
                    }
                    case STUDENTS: {
                        viewer.printCollection(controller.allStudents());
                        break;
                    }
                    case GROUPS: {
                        viewer.printCollection(controller.allGroups());
                        break;
                    }
                    case UPDATE: {
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
                        break;
                    }
                    case DELETE: {
                        viewer.delete();
                        int index = Integer.valueOf(reader.readLine());
                        controller.delete(index - 1);
                        break;
                    }
                    case EXIT:
                        System.exit(0);
                    case FIND: {
                        String[] str = new String[arr.length - 1];
                        for (int i = 0; i < str.length; i++)
                            str[i] = arr[i + 1];
                        if (str.length > 0) {
                            if (controller.find(str).isEmpty()) {
                                viewer.notFound(str);
                                break;
                            } else {
                                viewer.printCollection(controller.find(str));
                                break;
                            }
                        } else {
                            viewer.badCommand();
                            break;
                        }
                    }
                    case UNDO: {
                        controller.undo();
                        break;
                    }
                    case REMOVEGROUP: {
                        viewer.changeGroup(0);
                        String faculty = reader.readLine();
                        viewer.changeGroup(1);
                        String number = reader.readLine();
                        if (studPattern.wordMatcher(faculty) && studPattern.numberMatcher(number))
                            controller.deleteGroup(number, faculty);
                        else throw new FormarOfStudentException();
                        break;
                    }
                    case COPY: {
                        controller.copyFile();
                        break;
                    }
                    case RENAMEGROUP: {
                        viewer.changeGroup(0);
                        String faculty = reader.readLine();
                        viewer.changeGroup(1);
                        String number = reader.readLine();
                        viewer.changeGroup(2);
                        String renamedgroup = reader.readLine();
                        if (studPattern.wordMatcher(faculty) && studPattern.numberMatcher(number))
                            controller.renameGroup(faculty, number, renamedgroup);
                        else throw new FormarOfStudentException();
                        break;
                    }
                }

            } catch (IllegalArgumentException e) {
                viewer.badCommand();
            } catch (BadXmlFileException e) {
                viewer.xmlFileError();
            } catch (SAXException e) {
                viewer.xmlFileError();
            } catch (ParserConfigurationException e) {
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
            }

        }
    }
}
