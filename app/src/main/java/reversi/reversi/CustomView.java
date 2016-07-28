package reversi.reversi;

/**
 * Created by toutoune134 on 27/07/2016.
 */
// imports
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import reversi.reversi.Coordinates;

import java.util.ArrayList;
import java.util.Iterator;

// class definition
public class CustomView extends View {

    private Paint red, green, blue;
    private Rect square; // the square itself
    private boolean touches[]; // which fingers providing input
    private float touchx[]; // x position of each touch
    private float touchy[]; // y position of each touch
    private int first; // the first touch to be rendered
    private boolean touch; // do we have at least on touch
    private int x;
    private int y;
    private int old_x = 0;
    private int old_y = 0;
    private String Grid[][];
    private Paint paint_black;
    private Paint paint_white;
    private boolean white_turn;
    private int Remaingspots;
    private int CurrentBlackPieces;
    private int CurrentWhitePieces;
    private int CurrentTotalPieces;
    private  boolean NoOneCanPlay;
    private boolean hasplayed;

    // default constructor for the class that takes in a context
    public CustomView(Context c) {
        super(c);
        init();
    }
    // constructor that takes in a context and also a list of attributes
    // that were set through XML
    public CustomView(Context c, AttributeSet as) {
        super(c, as);
        init();
    }
    // constructor that take in a context, attribute set and also a default
    // style in case the view is to be styled in a certian way
    public CustomView(Context c, AttributeSet as, int default_style) {
        super(c, as, default_style);
        init();
    }
    // refactored init method as most of this code is shared by all the
    // constructors
    private void init() {

        paint_black = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint_white = new Paint(Paint.ANTI_ALIAS_FLAG);
        blue = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint_black.setColor(0xFF000000);
        paint_white.setColor(0xFFFFFFFF);
        blue.setColor(0xFF00B8FF);

        touches = new boolean[16];
        touchx = new float[16];
        touchy = new float[16];

        touchx[0] = 200.f;
        touchy[0] = 200.f;

        square = new Rect(0, 0, 45, 45);

        touch = false;


        Grid = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++ ){
                Grid[i][j] = "";
            }
        }
        Grid[4][3] = "black";
        Grid[3][4] = "black";
        Grid[3][3] = "white";
        Grid[4][4] = "white";

        white_turn = true;

        Remaingspots = 32;

        CurrentWhitePieces = 2;
        CurrentBlackPieces = 2;

        hasplayed = false;

        CurrentTotalPieces = 4;

    }


    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (y > x) {
            canvas.drawText("Black : " + CurrentBlackPieces, 10, y - 30, paint_black);
            canvas.drawText("White : " + CurrentWhitePieces, (float) 2/3 *  x  + 10, y - 30, paint_black);
            if (white_turn)
                canvas.drawText("Turn : White", (float) 1/3 * x + 10, y - 30, blue);
            else
                canvas.drawText("Turn : Black", (float) 1/3 * x + 10, y - 30, blue);
        } else {
            canvas.drawText("Black : " + CurrentBlackPieces, (float) 2/3 * x + 10, 10, paint_black);
            canvas.drawText("White : " + CurrentWhitePieces, (float) 2/3 * x + 10, 30, paint_black);
            if (white_turn)
                canvas.drawText("Turn : White", (float) 2/3 * x + 10 + 10, 50, blue);
            else
                canvas.drawText("Turn : Black", (float) 2/3 * x + 10 + 10, 50, blue);
        }

        int currentLenghtRectangle = x < y ? (x - 9*5) / 8 : (y - 9*5) / 8;

        for (float i = 0.f; i < 8.f; i++) {
            for (float j = 0.f; j < 8.f; j++) {
                square = new Rect(0,0,currentLenghtRectangle,currentLenghtRectangle );
                canvas.save();
                canvas.translate((currentLenghtRectangle + 5) * i + 5, (currentLenghtRectangle + 5) * j + 5);
                canvas.drawRect(square, blue);
                canvas.restore();
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Grid[i][j] == "black") {
                    canvas.save();
                    canvas.drawCircle(5 + (currentLenghtRectangle +5) * i + currentLenghtRectangle /2, 5 + (currentLenghtRectangle +5) * j + currentLenghtRectangle /2, currentLenghtRectangle /2 , paint_black);
                    canvas.restore();
                }
                if (Grid [i][j] == "white") {
                    canvas.save();
                    canvas.drawCircle(5 + (currentLenghtRectangle +5) * i + currentLenghtRectangle /2, 5 + (currentLenghtRectangle +5) * j + currentLenghtRectangle /2, currentLenghtRectangle /2 , paint_white);
                    canvas.restore();

                }
            }
        }

    }


    public boolean onTouchEvent(MotionEvent event) {

        if(event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            System.out.println("wxwxwwwwwwwwwwwwwwwwwwww " + white_turn);
            int currentLenghtRectangle = x < y ? (x - 9*5) / 8 : (y - 9*5) / 8;
            int touchedRectangle_X = ((int) event.getX() - ( (int) event.getX() % (currentLenghtRectangle + 5)) + (currentLenghtRectangle + 5) /2) / (currentLenghtRectangle + 5);
            int touchedRectangle_Y = ((int) event.getY() - ( (int) event.getY() % (currentLenghtRectangle + 5)) + (currentLenghtRectangle + 5) /2) / (currentLenghtRectangle + 5);

            if (hasplayed) {
                white_turn = !white_turn;
                hasplayed = false;
            }

            if (touchedRectangle_X >= 0 && touchedRectangle_X < 8 && touchedRectangle_Y >=0 && touchedRectangle_Y < 8) {
                if (Grid[touchedRectangle_X][touchedRectangle_Y] == "") {
                    if (white_turn)
                        isMoveValid(touchedRectangle_X, touchedRectangle_Y, "white");
                    else
                        isMoveValid(touchedRectangle_X, touchedRectangle_Y, "black");
                }
            }
            invalidate();
            return true;
        } else if(event.getActionMasked() == MotionEvent.ACTION_UP) {

        } else if(event.getActionMasked() == MotionEvent.ACTION_MOVE) {

        } else if(event.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN) {

        } else if(event.getActionMasked() == MotionEvent.ACTION_POINTER_UP) {

        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld)
    {
        x = xNew;
        y = yNew;
        old_x = xOld;
        old_y = yOld;

    }

    public boolean isMoveValid(int touchedRectangle_X, int touchedRectangle_Y, String color) {

        boolean convertColor = false;
        boolean atleastoneprey = false;
        boolean rootAlreadyAdded = false;
        ArrayList<Coordinates> list_to_convert = new ArrayList<>(36);
        ArrayList<Coordinates> list_to_convert_temp = new ArrayList<>(36);

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
            for (int i =  1; i < 8; i++) {
                if (touchedRectangle_X + i < 8 && touchedRectangle_Y + i >=0 && touchedRectangle_Y + i < 8 && touchedRectangle_X + i >=0) {
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
            for (int i =  1; i < 8; i++) {
                if (touchedRectangle_X - i < 8 && touchedRectangle_Y + i >=0 && touchedRectangle_Y + i < 8 && touchedRectangle_X - i >=0) {
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
            for (int i =  1; i < 8; i++) {
                if (touchedRectangle_X - i < 8 && touchedRectangle_Y - i >=0 && touchedRectangle_Y - i < 8 && touchedRectangle_X - i >=0) {
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
            for (int i =  1; i < 8; i++) {
                if (touchedRectangle_X + i < 8 && touchedRectangle_Y - i >=0 && touchedRectangle_Y - i < 8 && touchedRectangle_X + i >=0) {
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

        ConvertAll(list_to_convert,  color);

        return true;
    }

    public void ConvertAll(ArrayList<Coordinates> list_to_convert, String color) {


        if (list_to_convert.size() > 0) {
            hasplayed = true;
            if (color == "black") {
                //CurrentBlackPieces--;
                CurrentWhitePieces++;
            }
            if (color == "white") {
                //CurrentWhitePieces--;
                CurrentBlackPieces++;
            }
        }
        for (Coordinates corr : list_to_convert) {
            Grid[corr.getX()][corr.getY()] = color;
            if (color == "black") {
                CurrentBlackPieces++;
                CurrentWhitePieces--;
            }
            if (color == "white") {
                CurrentBlackPieces--;
                CurrentWhitePieces++;
            }
        }
        CurrentTotalPieces = CurrentBlackPieces + CurrentWhitePieces;
        System.out.println("black " + CurrentBlackPieces + ", " + "white :" + CurrentWhitePieces + " , size : " + list_to_convert.size());

    }

    public boolean CanPlayerPlay(boolean white_turn) {
        return true;
    }

    public boolean isGameOver() {
        if (CurrentTotalPieces == 36 || CurrentWhitePieces == 0 || CurrentBlackPieces == 0)
            return true;
        return true;
    }
}