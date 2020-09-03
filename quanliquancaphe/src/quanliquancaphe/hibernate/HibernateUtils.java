package quanliquancaphe.hibernate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import quanliquancaphe.models.Ban;
import quanliquancaphe.models.ChiTietDonHang;
import quanliquancaphe.models.DanhMuc;
import quanliquancaphe.models.HoaDon;
import quanliquancaphe.models.Mon;
import quanliquancaphe.models.NhanVien;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author admin
 */
public class HibernateUtils {

    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configurations = new Configuration();
            configurations.addAnnotatedClass(NhanVien.class);
            configurations.addAnnotatedClass(Ban.class);
            configurations.addAnnotatedClass(Mon.class);
            configurations.addAnnotatedClass(HoaDon.class);
            configurations.addAnnotatedClass(ChiTietDonHang.class);
            configurations.addAnnotatedClass(DanhMuc.class);
            configurations.configure("hibernate.cfg.xml");
            //sessionFactorys = new AnnotationConfiguration().configure().buildSessionFactory();
            StandardServiceRegistryBuilder builders = new StandardServiceRegistryBuilder()
                    .applySettings(configurations.getProperties());
            sessionFactory = configurations.buildSessionFactory(builders.build());
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
