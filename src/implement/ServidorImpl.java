package implement;

import java.io.File;
import servidor.InterfaceServidorCliente;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jhonatan
 */
public class ServidorImpl extends UnicastRemoteObject
        implements InterfaceServidorCliente {

    public ServidorImpl() throws RemoteException {
        super();
    }

    private static String path = null;
    private static File[] files = null;

    @Override
    public ImageIcon[] carregarFotos() {

        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        do {
            int j = jfc.showOpenDialog(null);

            if (j == JFileChooser.APPROVE_OPTION) {
                path = jfc.getSelectedFile().getAbsolutePath();
            }

            if (path == null) {
                path = System.getProperty("user.home") + "/Pictures";
            }
            File root = new File(path);

            files = root.listFiles(new FileImageFilter());

            if (files != null && files.length <= 0) {
                JOptionPane.showMessageDialog(jfc,
                        "O diretório escolhido não possui imagens.");
            }

        } while (files.length <= 0);

        ImageIcon[] ii = new ImageIcon[files.length];
        for (int i = 0; i < files.length; i++) {
            ii[i] = new ImageIcon(files[i].toString());
        }
        return ii;
    }

    @Override
    public ImageIcon[] carregarFotosSemConf() throws RemoteException {

        File root = new File(path);

        files = root.listFiles(new FileImageFilter());

        ImageIcon[] ii = new ImageIcon[files.length];
        for (int i = 0; i < files.length; i++) {
            ii[i] = new ImageIcon(files[i].toString());
        }
        return ii;
    }

    @Override
    public String obterPath() throws RemoteException {
        return path;
    }

    @Override
    public File[] obterFiles() throws RemoteException {
        return files;
    }

    @Override
    public boolean deletarFoto(int op, File file) throws RemoteException {
        if (op == JOptionPane.YES_OPTION) {
            return file.delete();
        } else {
            return true;
        }
    }
}
