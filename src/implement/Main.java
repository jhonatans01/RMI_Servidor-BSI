package implement;


import servidor.InterfaceServidorCliente;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jhonatan
 */
public class Main {

    public static void main(String[] args) {
        try {
            InterfaceServidorCliente is = new ServidorImpl();
            Registry registro = LocateRegistry.createRegistry(2020);
            registro.rebind("servidor", is);
            System.out.println("Pronto");
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
