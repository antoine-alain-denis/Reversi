package reversi.reversi;

import java.util.ArrayList;

/**
 * Created by toutoune134 on 28/07/2016.
 */
public class TurnSimulator {

    private String color;
    private String[][] Grid;

    public TurnSimulator( String[][] Grid) {

        this.Grid = Grid;
    }

    public boolean CanColorPlay(String color) {
        boolean convertColor = false;
        boolean atleastoneprey = false;
        boolean rootAlreadyAdded = false;
        ArrayList<Coordinates> list_to_convert = new ArrayList<>(36);
        ArrayList<Coordinates> list_to_convert_temp = new ArrayList<>(36);

        boolean CanPlayerPlay = false;

        for (int touchedRectangle_X = 0; touchedRectangle_X < 8; touchedRectangle_X++) {
            if (CanPlayerPlay)
                break;
            for (int touchedRectangle_Y = 0; touchedRectangle_Y < 8; touchedRectangle_Y++){

                if (CanPlayerPlay)
                    break;

                // case RIGHT
                if (Grid[touchedRectangle_X][touchedRectangle_Y] == "") {
                    Coordinates coor = new Coordinates(touchedRectangle_X, touchedRectangle_Y);
                    list_to_convert_temp.add(coor);
                    for (int i = touchedRectangle_X + 1; i < 8; i++) {
                        if (Grid[i][touchedRectangle_Y] == "")
                            break;
                        if (Grid[i][touchedRectangle_Y] != color) {
                            Coordinates coord = new Coordinates(i, touchedRectangle_Y);
                            list_to_convert_temp.add(coord);
                            atleastoneprey = true;
                        }
                        if (Grid[i][touchedRectangle_Y] == color) {
                            convertColor = true;
                            break;
                        }
                    }
                }
                if (convertColor && atleastoneprey) {
                    list_to_convert.addAll(list_to_convert_temp);
                    rootAlreadyAdded = true;
                    System.out.println("aaaaaaaaaaaaaaaaaaaaaa");
                }
                convertColor = false;
                atleastoneprey = false;
                list_to_convert_temp.clear();

                // case LEFT
                if (Grid[touchedRectangle_X][touchedRectangle_Y] == "") {
                    Coordinates coor = new Coordinates(touchedRectangle_X, touchedRectangle_Y);
                    if (!rootAlreadyAdded) {
                        list_to_convert_temp.add(coor);
                    }
                    for (int i = touchedRectangle_X - 1; i >= 0; i--) {
                        if (Grid[i][touchedRectangle_Y] == "")
                            break;
                        if (Grid[i][touchedRectangle_Y] != color) {
                            Coordinates coord = new Coordinates(i, touchedRectangle_Y);
                            list_to_convert_temp.add(coord);
                            atleastoneprey = true;
                        }
                        if (Grid[i][touchedRectangle_Y] == color) {
                            convertColor = true;
                            break;
                        }
                    }
                }
                if (convertColor && atleastoneprey) {
                    list_to_convert.addAll(list_to_convert_temp);
                    rootAlreadyAdded = true;
                    System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbb");

                }
                convertColor = false;
                atleastoneprey = false;
                list_to_convert_temp.clear();


                // case UP
                if (Grid[touchedRectangle_X][touchedRectangle_Y] == "") {
                    Coordinates coor = new Coordinates(touchedRectangle_X, touchedRectangle_Y);
                    if (!rootAlreadyAdded) {
                        list_to_convert_temp.add(coor);
                    }
                    for (int i = touchedRectangle_Y + 1; i < 8; i++) {
                        if (Grid[touchedRectangle_X][i] == "")
                            break;
                        if (Grid[touchedRectangle_X][i] != color) {
                            Coordinates coord = new Coordinates(touchedRectangle_X, i);
                            list_to_convert_temp.add(coord);
                            atleastoneprey = true;
                        }
                        if (Grid[touchedRectangle_X][i] == color) {
                            convertColor = true;
                            break;
                        }
                    }
                }
                if (convertColor && atleastoneprey) {
                    list_to_convert.addAll(list_to_convert_temp);
                    rootAlreadyAdded = true;
                    System.out.println("cccccccccccccccccccccccccc");

                }
                convertColor = false;
                atleastoneprey = false;
                list_to_convert_temp.clear();


                // case Down
                if (Grid[touchedRectangle_X][touchedRectangle_Y] == "") {
                    Coordinates coor = new Coordinates(touchedRectangle_X, touchedRectangle_Y);
                    if (!rootAlreadyAdded) {
                        list_to_convert_temp.add(coor);
                    }
                    for (int i = touchedRectangle_Y - 1; i >= 0; i--) {
                        if (Grid[touchedRectangle_X][i] == "")
                            break;
                        if (Grid[touchedRectangle_X][i] != color) {
                            Coordinates coord = new Coordinates(touchedRectangle_X, i);
                            list_to_convert_temp.add(coord);
                            atleastoneprey = true;
                        }
                        if (Grid[touchedRectangle_X][i] == color) {
                            convertColor = true;
                            break;
                        }
                    }
                }
                if (convertColor && atleastoneprey) {
                    list_to_convert.addAll(list_to_convert_temp);
                    rootAlreadyAdded = true;
                    System.out.println("dddddddddddddddddddddddddd");

                }
                convertColor = false;
                atleastoneprey = false;
                list_to_convert_temp.clear();


                // case UPRIGHT
                if (Grid[touchedRectangle_X][touchedRectangle_Y] == "") {
                    Coordinates coor = new Coordinates(touchedRectangle_X, touchedRectangle_Y);
                    if (!rootAlreadyAdded) {
                        list_to_convert_temp.add(coor);
                    }
                    for (int i = 1; i < 8; i++) {
                        if (touchedRectangle_X + i < 8 && touchedRectangle_Y + i >= 0 && touchedRectangle_Y + i < 8 && touchedRectangle_X + i >= 0) {
                            if (Grid[touchedRectangle_X + i][touchedRectangle_Y + i] == "")
                                break;
                            if (Grid[touchedRectangle_X + i][touchedRectangle_Y + i] != color) {
                                Coordinates coord = new Coordinates(touchedRectangle_X + i, touchedRectangle_Y + i);
                                list_to_convert_temp.add(coord);
                                atleastoneprey = true;
                            }
                            if (Grid[touchedRectangle_X + i][touchedRectangle_Y + i] == color) {
                                convertColor = true;
                                break;
                            }
                        }
                    }
                }
                if (convertColor && atleastoneprey) {
                    list_to_convert.addAll(list_to_convert_temp);
                    rootAlreadyAdded = true;
                    System.out.println("eeeeeeeeeeeeeeeeeeeeeee");
                }

                convertColor = false;
                atleastoneprey = false;
                list_to_convert_temp.clear();

                // case UPLEFT
                if (Grid[touchedRectangle_X][touchedRectangle_Y] == "") {
                    Coordinates coor = new Coordinates(touchedRectangle_X, touchedRectangle_Y);
                    if (!rootAlreadyAdded) {
                        list_to_convert_temp.add(coor);
                    }
                    for (int i = 1; i < 8; i++) {
                        if (touchedRectangle_X - i < 8 && touchedRectangle_Y + i >= 0 && touchedRectangle_Y + i < 8 && touchedRectangle_X - i >= 0) {
                            if (Grid[touchedRectangle_X - i][touchedRectangle_Y + i] == "")
                                break;
                            if (Grid[touchedRectangle_X - i][touchedRectangle_Y + i] != color) {
                                Coordinates coord = new Coordinates(touchedRectangle_X - i, touchedRectangle_Y + i);
                                list_to_convert_temp.add(coord);
                                atleastoneprey = true;
                            }
                            if (Grid[touchedRectangle_X - i][touchedRectangle_Y + i] == color) {
                                convertColor = true;
                                break;
                            }
                        }
                    }
                }
                if (convertColor && atleastoneprey) {
                    list_to_convert.addAll(list_to_convert_temp);
                    rootAlreadyAdded = true;
                    System.out.println("eeeeeeeeeeeeeeeeeeeeeee");
                }

                convertColor = false;
                atleastoneprey = false;
                list_to_convert_temp.clear();


                // case DOWNLEFT
                if (Grid[touchedRectangle_X][touchedRectangle_Y] == "") {
                    Coordinates coor = new Coordinates(touchedRectangle_X, touchedRectangle_Y);
                    if (!rootAlreadyAdded) {
                        list_to_convert_temp.add(coor);
                    }
                    for (int i = 1; i < 8; i++) {
                        if (touchedRectangle_X - i < 8 && touchedRectangle_Y - i >= 0 && touchedRectangle_Y - i < 8 && touchedRectangle_X - i >= 0) {
                            if (Grid[touchedRectangle_X - i][touchedRectangle_Y - i] == "")
                                break;
                            if (Grid[touchedRectangle_X - i][touchedRectangle_Y - i] != color) {
                                Coordinates coord = new Coordinates(touchedRectangle_X - i, touchedRectangle_Y - i);
                                list_to_convert_temp.add(coord);
                                atleastoneprey = true;
                            }
                            if (Grid[touchedRectangle_X - i][touchedRectangle_Y - i] == color) {
                                convertColor = true;
                                break;
                            }
                        }
                    }
                }
                if (convertColor && atleastoneprey) {
                    list_to_convert.addAll(list_to_convert_temp);
                    rootAlreadyAdded = true;
                    System.out.println("fffffffffffffffffffffffffffff");
                }

                convertColor = false;
                atleastoneprey = false;
                list_to_convert_temp.clear();


                // case DOWNRIGHT
                if (Grid[touchedRectangle_X][touchedRectangle_Y] == "") {
                    Coordinates coor = new Coordinates(touchedRectangle_X, touchedRectangle_Y);
                    if (!rootAlreadyAdded) {
                        list_to_convert_temp.add(coor);
                    }
                    for (int i = 1; i < 8; i++) {
                        if (touchedRectangle_X + i < 8 && touchedRectangle_Y - i >= 0 && touchedRectangle_Y - i < 8 && touchedRectangle_X + i >= 0) {
                            if (Grid[touchedRectangle_X + i][touchedRectangle_Y - i] == "")
                                break;
                            if (Grid[touchedRectangle_X + i][touchedRectangle_Y - i] != color) {
                                Coordinates coord = new Coordinates(touchedRectangle_X + i, touchedRectangle_Y - i);
                                list_to_convert_temp.add(coord);
                                atleastoneprey = true;
                            }
                            if (Grid[touchedRectangle_X + i][touchedRectangle_Y - i] == color) {
                                convertColor = true;
                                break;
                            }
                        }
                    }
                }
                if (convertColor && atleastoneprey) {
                    list_to_convert.addAll(list_to_convert_temp);
                    rootAlreadyAdded = true;
                    System.out.println("fffffffffffffffffffffffffffff");
                }

                convertColor = false;
                atleastoneprey = false;
                list_to_convert_temp.clear();

                if (list_to_convert.size() > 0) {
                    CanPlayerPlay = true;
                }

            }

        }


        System.out.println("fffffffffffffffffffffffffffff");
        return CanPlayerPlay;
    }
}
