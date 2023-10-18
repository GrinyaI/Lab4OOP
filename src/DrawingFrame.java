import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawingFrame extends JFrame implements ActionListener {

    private JPanel drawingArea;
    private Color currentColor;
    private int currentThickness;

    public DrawingFrame() {
        super("Полотно");

        drawingArea = new JPanel();
        drawingArea.setBackground(Color.WHITE);
        drawingArea.setPreferredSize(new Dimension(500, 500));
        drawingArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Graphics g = drawingArea.getGraphics();
                g.setColor(currentColor);
                ((Graphics2D) g).setStroke(new BasicStroke(currentThickness));
                g.drawLine(e.getX(), e.getY(), e.getX(), e.getY());
            }
        });
        drawingArea.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Graphics g = drawingArea.getGraphics();
                g.setColor(currentColor);
                ((Graphics2D) g).setStroke(new BasicStroke(currentThickness));
                g.drawLine(e.getX(), e.getY(), e.getX(), e.getY());
            }
        });

        JMenu colorMenu = new JMenu("Цвет");
        JMenuItem blackItem = new JMenuItem("Чёрный");
        blackItem.addActionListener(this);
        colorMenu.add(blackItem);
        JMenuItem redItem = new JMenuItem("Красный");
        redItem.addActionListener(this);
        colorMenu.add(redItem);
        JMenuItem blueItem = new JMenuItem("Синий");
        blueItem.addActionListener(this);
        colorMenu.add(blueItem);
        JMenuItem greenItem = new JMenuItem("Зелёный");
        greenItem.addActionListener(this);
        colorMenu.add(greenItem);
        JMenuItem yellowItem = new JMenuItem("Жёлтый");
        yellowItem.addActionListener(this);
        colorMenu.add(yellowItem);

        JMenu thicknessMenu = new JMenu("Толщина пера");
        JMenuItem thinItem = new JMenuItem("Тонкий");
        thinItem.addActionListener(this);
        thicknessMenu.add(thinItem);
        JMenuItem mediumItem = new JMenuItem("Средний");
        mediumItem.addActionListener(this);
        thicknessMenu.add(mediumItem);
        JMenuItem thickItem = new JMenuItem("Толстый");
        thickItem.addActionListener(this);
        thicknessMenu.add(thickItem);

        JMenuItem clean = new JMenuItem("Очистить");
        clean.addActionListener(this);

        JMenuItem eraser = new JMenuItem("Ластик");
        eraser.addActionListener(this);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(colorMenu);
        menuBar.add(thicknessMenu);
        menuBar.add(clean);
        menuBar.add(eraser);

        setJMenuBar(menuBar);
        add(drawingArea);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Чёрный" -> currentColor = Color.BLACK;
            case "Красный" -> currentColor = Color.RED;
            case "Синий" -> currentColor = Color.BLUE;
            case "Зелёный" -> currentColor = Color.GREEN;
            case "Жёлтый" -> currentColor = Color.YELLOW;
            case "Тонкий" -> currentThickness = 1;
            case "Средний" -> currentThickness = 5;
            case "Толстый" -> currentThickness = 10;
            case "Очистить" -> {
                Graphics g = drawingArea.getGraphics();
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, drawingArea.getWidth(), drawingArea.getHeight());
            }
            case "Ластик" -> currentColor = Color.WHITE;
        }
    }

    public static void main(String[] args) {
        new DrawingFrame();
    }
}