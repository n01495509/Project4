import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.NumberFormat;

/**
 * The type Custom j frame.
 */
public class CustomJFrame extends JFrame {

    private JLabel headingLabel;
    private JLabel firstNameLabel, lastNameLabel, phoneNumberLabel, emailLabel;
    private JLabel dietaryLabel, genderLabel, waterLabel, mealsLabel, checkBoxLabel, walkLabel, weightLabel;
    private JTextField firstNameTextField, lastNameTextField, phoneNumberTextField, emailTextField;
    private JRadioButton maleRadioButton, femaleRadioButton, preferRadioButton;
    private ButtonGroup radioButtonGroup;
    private JSpinner waterIntakeSpinner;
    private JSlider mealSlider;
    private JCheckBox wheatCheckBox, sugarCheckBox, dairyCheckBox;
    private JComboBox<String> walkComboBox;
    private String[] walkOptions = {"Less than 1 Mile", "More than 1 mile but less than 2 miles", "More than 2 miles but less than 3 miles", "More than 3 miles"};
    private JFormattedTextField weightFormattedTextField;
    private JButton clearButton, submitButton;
    private FileHandler fileHandler;

    /**
     * Instantiates a new Custom j frame.
     */
    public CustomJFrame() {
        setTitle("Dietary Survey");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);
        fileHandler = new FileHandler();

        headingLabel = new JLabel("Personal Information");
        firstNameLabel = new JLabel("First Name:");
        lastNameLabel = new JLabel("Last Name:");
        phoneNumberLabel = new JLabel("Phone Number:");
        emailLabel = new JLabel("Email:");
        dietaryLabel = new JLabel("Dietary Questions");
        genderLabel = new JLabel("Sex:");
        waterLabel = new JLabel("How many cups of water on average do you drink a day?");
        mealsLabel = new JLabel("How many meals on average do you eat a day?");
        checkBoxLabel = new JLabel("Do any of these meals regularly contain:");
        walkLabel = new JLabel("On Average how many miles do you walk a day?");
        weightLabel = new JLabel("How much do you weigh?");

        firstNameTextField = new JTextField(20);
        lastNameTextField = new JTextField(20);
        phoneNumberTextField = new JTextField(20);
        emailTextField = new JTextField(20);

        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        preferRadioButton = new JRadioButton("Prefer Not To Say");
        maleRadioButton.setActionCommand("Male");
        femaleRadioButton.setActionCommand("Female");
        preferRadioButton.setActionCommand("Prefer Not To Say");
        radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(maleRadioButton);
        radioButtonGroup.add(femaleRadioButton);
        radioButtonGroup.add(preferRadioButton);

        waterIntakeSpinner = new JSpinner(new SpinnerNumberModel(15, 0, 50, 1));

        mealSlider = new JSlider(0, 10, 3);
        mealSlider.setMinorTickSpacing(1);
        mealSlider.setMajorTickSpacing(1);
        mealSlider.setPaintTicks(true);
        mealSlider.setPaintLabels(true);

        wheatCheckBox = new JCheckBox("Wheat");
        sugarCheckBox = new JCheckBox("Sugar");
        dairyCheckBox = new JCheckBox("Dairy");

        walkComboBox = new JComboBox<>(walkOptions);

        Format numberFormat = NumberFormat.getIntegerInstance();
        weightFormattedTextField = new JFormattedTextField(numberFormat);
        weightFormattedTextField.setValue(null);
        weightFormattedTextField.setColumns(10);

        submitButton = new JButton("Submit");
        clearButton = new JButton("Clear");

        gbc.anchor = GridBagConstraints.CENTER;
        addComponent(headingLabel, 0, 0, gbc);
        addComponent(firstNameLabel, 0, 1, gbc);
        addComponent(firstNameTextField, 1, 1, gbc);
        addComponent(lastNameLabel, 0, 2, gbc);
        addComponent(lastNameTextField, 1, 2, gbc);
        addComponent(phoneNumberLabel, 0, 3, gbc);
        addComponent(phoneNumberTextField, 1, 3, gbc);
        addComponent(emailLabel, 0, 4, gbc);
        addComponent(emailTextField, 1, 4, gbc);
        addComponent(genderLabel, 0, 5, gbc);
        addComponent(maleRadioButton, 1, 5, gbc);
        addComponent(femaleRadioButton, 1, 6, gbc);
        addComponent(preferRadioButton, 1, 7, gbc);
        addComponent(dietaryLabel, 0, 8, gbc);
        addComponent(waterLabel, 0, 9, gbc);
        addComponent(waterIntakeSpinner, 0, 10, gbc);
        addComponent(mealsLabel, 0, 11, gbc);
        addComponent(mealSlider, 0, 12, gbc);
        addComponent(checkBoxLabel, 0, 13, gbc);
        addComponent(wheatCheckBox, 0, 14, gbc);
        addComponent(sugarCheckBox, 0, 15, gbc);
        addComponent(dairyCheckBox, 0, 16, gbc);
        addComponent(walkLabel, 0, 17, gbc);
        addComponent(walkComboBox, 0, 18, gbc);
        addComponent(weightLabel, 0, 19, gbc);
        addComponent(weightFormattedTextField, 0, 20, gbc);
        addComponent(submitButton, 0, 21, gbc);
        addComponent(clearButton, 1, 21, gbc);
        pack();

        InnerActionListener listener = new InnerActionListener();
        clearButton.addActionListener(listener);
        submitButton.addActionListener(listener);
    }

    private class InnerActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearButton) {
                clearForm();
            } else if (e.getSource() == submitButton) {
                String surveyData = collectSurveyData();
                fileHandler.writeResults(surveyData);
                clearForm();
            }
        }

        private void clearForm() {
            firstNameTextField.setText("");
            lastNameTextField.setText("");
            phoneNumberTextField.setText("");
            emailTextField.setText("");
            radioButtonGroup.clearSelection();
            waterIntakeSpinner.setValue(15);
            mealSlider.setValue(3);
            wheatCheckBox.setSelected(false);
            sugarCheckBox.setSelected(false);
            dairyCheckBox.setSelected(false);
            walkComboBox.setSelectedIndex(0);
            weightFormattedTextField.setValue(null);

        }
    }

    private String collectSurveyData() {
        String surveyData = textHandler(firstNameTextField) + ",";
        surveyData += textHandler(lastNameTextField) + ",";
        surveyData += textHandler(phoneNumberTextField) + ",";
        surveyData += textHandler(emailTextField) + ",";

        String selectedButton = null;
        if (radioButtonGroup.getSelection() != null) {
            selectedButton = radioButtonGroup.getSelection().getActionCommand();
        }

        surveyData += selectedButton + ",";
        surveyData += waterIntakeSpinner.getValue() + ",";
        surveyData += mealSlider.getValue() + ",";
        surveyData += wheatCheckBox.isSelected() + ",";
        surveyData += sugarCheckBox.isSelected() + ",";
        surveyData += dairyCheckBox.isSelected() + ",";
        surveyData += walkComboBox.getSelectedItem() + ",";

        String weight;
        if (weightFormattedTextField.getText().isEmpty()) {
            weight = "null";
        } else {
            weight = weightFormattedTextField.getText();
        }
        surveyData += weight;
        return surveyData;
    }

    private String textHandler(JTextField textField) {
        String text = textField.getText().trim();
        if (text.isEmpty()) {
            return "";
        }
        return text;
    }

    private void addComponent(Component component, int x, int y, GridBagConstraints gbc) {
        gbc.gridx = x;
        gbc.gridy = y;
        add(component, gbc);
    }


}