package com.mycompany.criptomessage;
//Librerias JCE para encriptar.

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

//Librerias Twilio para mandar el SMS al celular.
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

//Libreria para la seleccion, guardado, y gestion de los archivos.
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;

public class WindowView extends javax.swing.JFrame {
    //Aca tenemos las variables iniciadas que utilizaremos a lo largo del codigo.

    int codigoDesencriptado = 0; //Este sera un numero random que mandaremos por SMS para poder desencriptar.
    KeyGenerator keygenerator = null; //La Key Generator
    SecretKey myDesKey = null; //La llave secreta
    Cipher desCipher; //El cipher
    byte[] textEncrypted = null; //Y donde guardaremos el texto encriptado.

    public WindowView() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Enviar Mensaje");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Codigo:");

        jButton5.setText("Encriptar Archivo");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Desencriptado");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton2.setText("Encriptar Carpeta");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("DesencriptarCarpeta");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(15, 15, 15)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addGap(57, 57, 57)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Este es el boton de envio de mensaje.
        //Debajo generamos un numero random de 1 a 4 digitos de 1 a 3693.
        codigoDesencriptado = (int) (Math.random() * 3693 + 1);
        //Aqui ponemos valores que obtuvimos de la pagina que nos permite usar sus servicios de envio de SMS.
        String ACCOUNT_SID = System.getenv("AC00b72cd7400c97f435a0d71b04898f84");
        String AUTH_TOKEN = System.getenv("78cde2a6e87f7f4b7dbda1804563434e");

        //Aca tambien ingresamos solamente los mismos datos que arriba, los puse directamente ya que
        //por alguna razon daba error si no lo hacia.
        Twilio.init("AC00b72cd7400c97f435a0d71b04898f84", "78cde2a6e87f7f4b7dbda1804563434e");
        //Creamos un objeto de tipo mensaje con los dos numeros, uno que es el nuestro real
        //Y el otro es uno que nos brinda la pagina de twilio.
        //Por ultimo es el mensaje que se enviara, en este caso "la clave es:"+ el numero que debemos de ingresar.
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+526142463319"),
                new com.twilio.type.PhoneNumber("+15136471450"),
                "La clave es: " + codigoDesencriptado)
                .create();
        //Esto mostrara en consola que si se pudo enviar o no el mensaje.
        System.out.println(message.getSid());


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        //Entonces, procedemos a crear un objeto de tipo fileChooser
        JFileChooser fileChooser = new JFileChooser();
        //Con esto, abrimos la ventana para elegir el archivo.
        int returnValue = fileChooser.showOpenDialog(null);
        //Si le damos al boton de aceptar o abrir, entonces hara lo sigueinte.
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            //Primeramente, crearemos un objeto de tipo File
            //Con el objeto que tomamos o el seleccionado en la ventana pues.
            File inputFile = fileChooser.getSelectedFile();

            try {

                //Esto ya lo habiamos hecho anteriormente, simplemente es todo el proceso de encriptado.
                //En este caso en vez de DES / AES / RC4, estamos usando el triple des,
                //En este caso debemos escribir DESede
                keygenerator = KeyGenerator.getInstance("DESede");
                //Y tambien tenemos que darle un tamaño de llave, en este caso de 168.
                keygenerator.init(168);
                //Generamos la llave entonces.
                myDesKey = keygenerator.generateKey();
                // creamos el cifrador. //En este caso usamos los siguientes datos por defecto
                //Su ECB, y el padding respectivo.
                desCipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
                //Inicializamos el metodo de encriptado del cipher.
                desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
                //Y ahora haremos uso del FileInputStream, con esto
                //Tomaremos el archivo previamente seleccionado y lo convertiremos a un arreglo de bytes.
                FileInputStream inputStream = new FileInputStream(inputFile);
                byte[] inputBytes = new byte[(int) inputFile.length()];
                //Y leeremos su contenido como tal.
                inputStream.read(inputBytes);
                //Y le daremos salida en su forma encriptada por asi decirlo.
                byte[] outputBytes = desCipher.doFinal(inputBytes);

                //Aqui, estoy abriendo otra ventana de explorador de archivos pero ahora en modo de 
                //guardado, para seleccionar donde guardaremos el archivo encriptado.
                JFileChooser saveFileChooser = new JFileChooser();
                int saveReturnValue = saveFileChooser.showSaveDialog(null);
                //Si le damos en ok o aceptar o seleccionar entonces.
                if (saveReturnValue == JFileChooser.APPROVE_OPTION) {
                    //entonces procederemos a guardar el archivo.
                    File encryptedFile = saveFileChooser.getSelectedFile();
                    //Tecnicamente es crear un archivo outputStream, con el otro archivo encriptado.
                    FileOutputStream outputStream = new FileOutputStream(encryptedFile);
                    //Escribirlo
                    outputStream.write(outputBytes);
                    //Y cerrarlo y ya estaria.
                    outputStream.close();
                }

                inputStream.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        //En el desencriptado es mas de lo mismo, solo mostrare lo que cambia.
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File inputFile = fileChooser.getSelectedFile();
            //En este caso, primeramente lo que cambia es que pide que coincida la llave introducida
            //En el JtextField con la que se genero previamente con el boton de enviar mensaje.
            if (jTextField1.getText().equalsIgnoreCase(String.valueOf(codigoDesencriptado))) {
                try {
                    //Iniciamos el cipher otra vez pero ahora en modo de desencriptado.
                    desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
                    //Internamente aqui todo es lo mismo, unicamente que sera desencriptado.
                    FileInputStream inputStream = new FileInputStream(inputFile);
                    byte[] inputBytes = new byte[(int) inputFile.length()];
                    inputStream.read(inputBytes);

                    byte[] outputBytes = desCipher.doFinal(inputBytes);

                    JFileChooser saveFileChooser = new JFileChooser();
                    int saveReturnValue = saveFileChooser.showSaveDialog(null);

                    if (saveReturnValue == JFileChooser.APPROVE_OPTION) {
                        File encryptedFile = saveFileChooser.getSelectedFile();
                        FileOutputStream outputStream = new FileOutputStream(encryptedFile);
                        outputStream.write(outputBytes);
                        outputStream.close();
                    }

                    inputStream.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //Este es el metodo de encriptado pero de una careta completa, no de un archivo nomas.
        //En este caso creamos un objeto de tipo JFile Chooser llamado folderChooser.
        JFileChooser folderChooser = new JFileChooser();
        //Con el metodo debajo, le decimos que el archivo a seleccionar es unicamente directorios
        //O sea no otro tipo de archivo mas que ese.
        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = folderChooser.showOpenDialog(null);
        //Abrimos la ventana de seleccion y de igual forma si la opcion es abrir o seleccionar entonces
        //empezara lo siguiente.
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            //Crearemos un archivo con la carpeta seleecionada.
            File selectedFolder = folderChooser.getSelectedFile();

            try {

                //Empezaremos pero ahora con el metodo de encriptado AES, USANDO UN TAMAÑO DE LLAVE 128.
                keygenerator = KeyGenerator.getInstance("AES");
                keygenerator.init(128);
                myDesKey = keygenerator.generateKey();
                //crearemos un raw para poder utilizarlo junto con el AES y crear la secretKeySpeec, y con ello poder encriptar.
                byte[]raw = myDesKey.getEncoded();
                
                SecretKeySpec skeySpec = new SecretKeySpec(raw,"AES");
                // Create the cipher
                desCipher = Cipher.getInstance("AES");
                // Initialize the cipher for encryption
                desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
                //Y aqui el cambio con el de un solo archivo
                //Primeramente checamos la ruta donde esta la carpeta
                //En este caso para saber la ruta y ademas, creamos otro llamado
                //targetPath donde le asignaremos la carpeta donde se guardara
                //En este caso en el disco duro, y en la carpeta llamada Encriptados.
                Path sourcePath = selectedFolder.toPath();
                Path targetPath = Paths.get("C:\\Encriptados");
                //Aqui estamos haciendo como un "arbol" por asi decirlo de la carpeta
                //O sea estamos buscando en todas las ramas o todos los archivos.
                //Este metodo si lo busque por completo porque de verdad batalle demasiado pero aqui esta.
                Files.walk(sourcePath)
                        .filter(Files::isRegularFile)
                        .forEach(file -> {
                            try {
                                //A fin de cuentas esta buscando todos los archivos disponibles.
                                byte[] inputBytes = Files.readAllBytes(file);
                                //Los convierte a arreglos de Bytes y los encripta.
                                byte[] outputBytes = desCipher.doFinal(inputBytes);
                                //Y empieza a guardarlos en la carpeta que creamos previamente.
                                Path encryptedPath = targetPath.resolve(sourcePath.relativize(file));
                                Files.createDirectories(encryptedPath.getParent());
                                Files.write(encryptedPath, outputBytes);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        });
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        //En el sentido de desencriptado es mas de lo mismo, lo unico que cambia es que cambiaremos el metodo
        //a desencriptado en el cipher.
        JFileChooser folderChooser = new JFileChooser();
        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = folderChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = folderChooser.getSelectedFile();
            if (jTextField1.getText().equalsIgnoreCase(String.valueOf(codigoDesencriptado))) {
                try {

                    desCipher.init(Cipher.DECRYPT_MODE, myDesKey);

                    Path sourcePath = selectedFolder.toPath();
                    Path targetPath = Paths.get("C:\\Desencriptados");
                    Files.walk(sourcePath)
                            .filter(Files::isRegularFile)
                            .forEach(file -> {
                                try {
                                    byte[] inputBytes = Files.readAllBytes(file);
                                    byte[] outputBytes = desCipher.doFinal(inputBytes);
                                    Path encryptedPath = targetPath.resolve(sourcePath.relativize(file));
                                    Files.createDirectories(encryptedPath.getParent());
                                    Files.write(encryptedPath, outputBytes);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            });
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WindowView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
