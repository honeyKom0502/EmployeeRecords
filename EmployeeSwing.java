import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class EmployeeSwing {
    public EmployeeSwing() {
        JFrame frame = new JFrame("Employee Records");
        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().setBackground(Color.LIGHT_GRAY); 

        JLabel idLabel = new JLabel("ID:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel deptLabel = new JLabel("Department:");
        JLabel designationLabel = new JLabel("Designation:");
        JLabel salaryLabel = new JLabel("Salary:");
        JLabel statusLabel = new JLabel("Status:");
        JLabel filterDeptLabel = new JLabel("Filter by Dept:");
        JLabel filterStatusLabel = new JLabel("Filter by Status:");
        JLabel searchLabel = new JLabel("Search by ID:");

        JTextField idField = new JTextField(10);
        JTextField nameField = new JTextField(10);
        JTextField salaryField = new JTextField(10);
        JTextField searchField = new JTextField(10);

        String[] departments = {"HR", "Finance", "Engineering", "Marketing"};
        JComboBox<String> deptBox = new JComboBox<>(departments);

        String[] designations = {"Manager", "Developer", "Analyst", "Intern"};
        JComboBox<String> designationBox = new JComboBox<>(designations);

        String[] statuses = {"Active", "Inactive"};
        JComboBox<String> statusBox = new JComboBox<>(statuses);

        JButton addButton = new JButton("Submit");

        DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID", "Name", "Dept", "Designation", "Salary", "Status"}, 0);
        JTable table = new JTable(model);

        String[] filterDepartments = {"All", "HR", "Finance", "Engineering", "Marketing"};
        JComboBox<String> filterDeptBox = new JComboBox<>(filterDepartments);

        String[] filterStatuses = {"All", "Active", "Inactive"};
        JComboBox<String> filterStatusBox = new JComboBox<>(filterStatuses);

        JButton filterButton = new JButton("Filter");
        JButton searchButton = new JButton("Search");

        frame.add(idLabel); frame.add(idField);
        frame.add(nameLabel); frame.add(nameField);
        frame.add(deptLabel); frame.add(deptBox);
        frame.add(designationLabel); frame.add(designationBox);
        frame.add(salaryLabel); frame.add(salaryField);
        frame.add(statusLabel); frame.add(statusBox);
        frame.add(addButton);

        frame.add(filterDeptLabel); frame.add(filterDeptBox);
        frame.add(filterStatusLabel); frame.add(filterStatusBox);
        frame.add(filterButton);

        frame.add(searchLabel); frame.add(searchField);
        frame.add(searchButton);
        frame.add(new JScrollPane(table));

        addButton.addActionListener(e -> {
            model.addRow(new Object[]{
                idField.getText(), nameField.getText(), deptBox.getSelectedItem(),
                designationBox.getSelectedItem(), salaryField.getText(), statusBox.getSelectedItem()
            });

            idField.setText("");
            nameField.setText("");
            salaryField.setText("");
        });

        filterButton.addActionListener(e -> {
            String selectedDept = filterDeptBox.getSelectedItem().toString();
            String selectedStatus = filterStatusBox.getSelectedItem().toString();

            DefaultTableModel filteredModel = new DefaultTableModel(
                new String[]{"ID", "Name", "Dept", "Designation", "Salary", "Status"}, 0
            );

            for (int i = 0; i < model.getRowCount(); i++) {
                String dept = model.getValueAt(i, 2).toString();
                String status = model.getValueAt(i, 5).toString();

                boolean matchesDept = selectedDept.equals("All") || dept.equals(selectedDept);
                boolean matchesStatus = selectedStatus.equals("All") || status.equals(selectedStatus);

                if (matchesDept && matchesStatus) {
                    filteredModel.addRow(new Object[]{
                        model.getValueAt(i, 0), model.getValueAt(i, 1), model.getValueAt(i, 2),
                        model.getValueAt(i, 3), model.getValueAt(i, 4), model.getValueAt(i, 5)
                    });
                }
            }

            table.setModel(filteredModel);
        });

        searchButton.addActionListener(e -> {
            String searchText = searchField.getText().toLowerCase();
            for (int i = 0; i < model.getRowCount(); i++) {
                String id = model.getValueAt(i, 0).toString().toLowerCase();
                String name = model.getValueAt(i, 1).toString().toLowerCase();

                if (id.contains(searchText) || name.contains(searchText)) {
                    table.setRowSelectionInterval(i, i);
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new EmployeeSwing();
    }
}
