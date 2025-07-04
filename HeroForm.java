package view;

import model.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class HeroForm extends javax.swing.JFrame {
    private HeroDAO dao = new HeroDAO();
    private DefaultTableModel model;

    public HeroForm() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Pendataan Hero Mobile Legend");

        cbKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
            "MAGE", "ASSASIN", "FIGHTER", "TANK", "MARKSMAN", "SUPPORT"
        }));

        cbGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
            "MALE", "FEMALE"
        }));

        model = (DefaultTableModel) tblHero.getModel();
        loadData();
    }

    private void loadData() {
        model.setRowCount(0);
        List<Hero> list = dao.getAll();
        for (Hero h : list) {
            model.addRow(new Object[]{ h.getId(), h.getNama(), h.getKategori(), h.getGender() });
        }
    }

    private void resetForm() {
        txtNama.setText("");
        cbKategori.setSelectedIndex(0);
        cbGender.setSelectedIndex(0);
        tblHero.clearSelection();
    }

    // initComponents() dibuat otomatis oleh GUI Builder NetBeans
    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        cbKategori = new javax.swing.JComboBox<>();
        cbGender = new javax.swing.JComboBox<>();
        btnSimpan = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHero = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Pendataan Hero Mobile Legend");
        jLabel2.setText("Nama Hero");
        jLabel3.setText("Kategori");
        jLabel4.setText("Gender");

        btnSimpan.setText("Simpan");
        btnUbah.setText("Ubah");
        btnHapus.setText("Hapus");
        btnReset.setText("Reset");

        tblHero.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] { "ID", "Nama Hero", "Kategori", "Gender" }
        ));
        tblHero.getSelectionModel().addListSelectionListener(e -> {
            int row = tblHero.getSelectedRow();
            if (row >= 0) {
                txtNama.setText(model.getValueAt(row, 1).toString());
                cbKategori.setSelectedItem(model.getValueAt(row, 2).toString());
                cbGender.setSelectedItem(model.getValueAt(row, 3).toString());
            }
        });

        jScrollPane1.setViewportView(tblHero);

        // ActionListeners
        btnSimpan.addActionListener(e -> {
            String nama = txtNama.getText();
            String kategori = cbKategori.getSelectedItem().toString();
            String gender = cbGender.getSelectedItem().toString();
            dao.insert(new Hero(nama, kategori, gender));
            loadData();
            resetForm();
        });

        btnUbah.addActionListener(e -> {
            int row = tblHero.getSelectedRow();
            if (row >= 0) {
                int id = Integer.parseInt(model.getValueAt(row, 0).toString());
                String nama = txtNama.getText();
                String kategori = cbKategori.getSelectedItem().toString();
                String gender = cbGender.getSelectedItem().toString();
                dao.update(new Hero(id, nama, kategori, gender));
                loadData();
                resetForm();
            }
        });

        btnHapus.addActionListener(e -> {
            int row = tblHero.getSelectedRow();
            if (row >= 0) {
                int id = Integer.parseInt(model.getValueAt(row, 0).toString());
                dao.delete(id);
                loadData();
                resetForm();
            }
        });

        btnReset.addActionListener(e -> resetForm());

        // Layout dengan GroupLayout (hasil generate GUI builder)
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(20)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNama)
                                .addComponent(cbKategori, 0, 150, Short.MAX_VALUE)
                                .addComponent(cbGender, 0, 150, Short.MAX_VALUE))))
                    .addGap(20))
                .addGroup(layout.createSequentialGroup()
                    .addGap(20)
                    .addComponent(btnSimpan)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnUbah)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnHapus)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnReset))
                .addGroup(layout.createSequentialGroup()
                    .addGap(20)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(10)
                    .addComponent(jLabel1)
                    .addGap(15)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(cbKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(cbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(15)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSimpan)
                        .addComponent(btnUbah)
                        .addComponent(btnHapus)
                        .addComponent(btnReset))
                    .addGap(15)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }

    // Komponen
    private javax.swing.JButton btnSimpan, btnUbah, btnHapus, btnReset;
    private javax.swing.JComboBox<String> cbKategori, cbGender;
    private javax.swing.JLabel jLabel1, jLabel2, jLabel3, jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblHero;
    private javax.swing.JTextField txtNama;
}
