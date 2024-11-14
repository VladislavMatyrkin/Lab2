package bsu.rfe.java.group6.lab1.matyrkin.var14B;

// Импортируются классы, используемые в приложении
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
@SuppressWarnings("serial")
// Главный класс приложения, он же класс фрейма
public class MainFrame extends JFrame {
    // Размеры окна приложения в виде констант
    private static final int WIDTH = 700;
    private static final int HEIGHT = 420;
    // Текстовые поля для считывания значений переменных,
// как компоненты, совместно используемые в различных методах
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ; // Поле ввода для Z
    // Текстовое поле для отображения результата,
// как компонент, совместно используемый в различных методах
    private JTextField textFieldResult;
    private ButtonGroup formulaButtons = new ButtonGroup();
    private Box hboxFormulaType = Box.createHorizontalBox();
    // Группа для выбора переменной памяти
    private ButtonGroup memoryButtons = new ButtonGroup();
    // Панель для выбора переменной памяти
    private Box hboxMemoryType = Box.createHorizontalBox();
    // Идентификатор выбранной формулы
    private int formulaId = 1;
    // Активная переменная памяти
    private int memoryId = 1;

    private Double mem1 = 0.0, mem2 = 0.0, mem3 = 0.0; // Переменные памяти

    // Формула №1
    public Double calculate1(Double x, Double y, Double z) {
        return Math.pow(Math.log(z) + Math.sin(Math.PI * Math.pow(z, 2)), 0.25) /
                Math.pow(y * y + Math.exp(Math.cos(x)) + Math.sin(y), Math.sin(x));
    }

    // Формула №2
    public Double calculate2(Double x, Double y, Double z) {
        return Math.sqrt(y) * (3 * Math.pow(z, x) / Math.sqrt(1 + Math.pow(y, 3)));
    }

    // Добавление радио-кнопки для выбора формулы
    private void addRadioButton(String buttonName, final int formulaId) {
        // Создать экземпляр радио-кнопки с заданным текстом
        JRadioButton button = new JRadioButton(buttonName);
        // Определить и зарегистрировать обработчик
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                // Который будет устанавливать идентификатор выбранной
                // формулы в классе Formula равным formulaId
                MainFrame.this.formulaId = formulaId;
            }
        });
        formulaButtons.add(button);
        hboxFormulaType.add(button);
    }
    // Добавление радио-кнопки для выбора переменной памяти
    private void addMemoryButton(String buttonName, final int memoryId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.memoryId = memoryId;
            }
        });
        memoryButtons.add(button);
        hboxMemoryType.add(button);
    }
    // Конструктор класса
    public MainFrame() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
// Отцентрировать окно приложения на экране
        setLocation((kit.getScreenSize().width - WIDTH) / 2,
                (kit.getScreenSize().height - HEIGHT) / 2);
// Настройка панели выбора формулы
// Добавить «клей» C1-H1 с левой стороны
        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
// Установить выделенной 1-ую кнопку из группы
        formulaButtons.setSelected(formulaButtons.getElements().nextElement().getModel(), true);
// Добавить «клей» C1-H2 с правой стороны
        hboxFormulaType.add(Box.createHorizontalGlue());
// Задать рамку для коробки с помощью класса BorderFactory
        hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        // Создать область с полями ввода для X,Y,Z
        JLabel labelForX = new JLabel("X:");
        // Создать текстовое поле для ввода значения переменной X,
        // (по умолчанию 0)
        textFieldX = new JTextField("0", 10);
        // Установить макс размер = желаемому для предотвращения масштабирования
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:"); // Метка для Z
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());

        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(
                BorderFactory.createLineBorder(Color.RED));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);
        hboxVariables.add(Box.createHorizontalGlue());

        // Настройка панели выбора переменной памяти
        hboxMemoryType.add(Box.createHorizontalGlue());
        addMemoryButton("Переменная 1", 1);
        addMemoryButton("Переменная 2", 2);
        addMemoryButton("Переменная 3", 3);
        memoryButtons.setSelected(
                memoryButtons.getElements().nextElement().getModel(), true);
        hboxMemoryType.add(Box.createHorizontalGlue());
        hboxMemoryType.setBorder(
                BorderFactory.createLineBorder(Color.ORANGE));

        // Создание области для вывода результата
        JLabel labelForResult = new JLabel("Результат:");
        textFieldResult = new JTextField("0", 15);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        // Создание кнопок вычисления и сброса
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    // Выбор формулы
                    if (formulaId == 1)
                        result = calculate1(x, y, z);
                    else
                        result = calculate2(x, y, z);
                    // Установить текст надписи равным результату
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0"); // Очистка поля Z
                textFieldResult.setText("0");
            }
        });

        // Добавление кнопки MC
        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                switch (memoryId) {
                    case 1: mem1 = 0.0; break;
                    case 2: mem2 = 0.0; break;
                    case 3: mem3 = 0.0; break;
                }
                JOptionPane.showMessageDialog(MainFrame.this,
                        "Память очищена", "MC", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Добавление кнопки M+
        JButton buttonMPlus = new JButton("M+");
        buttonMPlus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double result = Double.parseDouble(textFieldResult.getText());
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    switch (memoryId) {
                        case 1:
                            mem1 +=result;
                            break;
                        case 2:
                            mem2 +=result;
                            break;
                        case 3:
                            mem3 += result;
                            break;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        // Добавление кнопки для показа значений памяти
        JButton buttonShowMemory = new JButton("Показать память");
        buttonShowMemory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                String memoryContent = "Переменная 1: " + mem1 + "\n" +
                        "Переменная 2: " + mem2 + "\n" +
                        "Переменная 3: " + mem3;
                JOptionPane.showMessageDialog(MainFrame.this, memoryContent, "Текущие значения памяти",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        // Создать коробку с горизонтальной укладкой
        Box hboxButtons = Box.createHorizontalBox();
// Добавить «клей» C4-H1 с левой стороны
        hboxButtons.add(Box.createHorizontalGlue());
// Добавить кнопку «Вычислить» в компоновку
        hboxButtons.add(buttonCalc);
// Добавить распорку в 30 пикселов C4-H2 между кнопками
        hboxButtons.add(Box.createHorizontalStrut(30));
// Добавить кнопку «Очистить поля» в компоновку
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonMC);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonMPlus);
// Добавить новую кнопку для показа памяти
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonShowMemory);
// Добавить «клей» C4-H3 с правой стороны
        hboxButtons.add(Box.createHorizontalGlue());
// Задать рамку для контейнера
        hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));
// Связать области воедино в компоновке BoxLayout
// Создать контейнер «коробка с вертикальной укладкой»
        Box contentBox = Box.createVerticalBox();
// Добавить «клей» V1 сверху
        contentBox.add(Box.createVerticalGlue());
// Добавить контейнер с выбором переменной
        contentBox.add(hboxMemoryType);
// Добавить контейнер с выбором формулы
        contentBox.add(hboxFormulaType);
// Добавить контейнер с переменными
        contentBox.add(hboxVariables);
// Добавить контейнер с результатом вычислений
        contentBox.add(hboxResult);
// Добавить контейнер с кнопками
        contentBox.add(hboxButtons);
// Добавить «клей» V2 снизу
        contentBox.add(Box.createVerticalGlue());
// Установить «вертикальную коробку» в область содержания главного окна
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }
    // Главный метод класса
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}