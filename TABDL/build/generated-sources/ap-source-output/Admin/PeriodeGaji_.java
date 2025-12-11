package Admin;

import Admin.ListGaji_1;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-12-09T14:20:16", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(PeriodeGaji.class)
public class PeriodeGaji_ { 

    public static volatile SingularAttribute<PeriodeGaji, Integer> idPeriodeGaji;
    public static volatile SingularAttribute<PeriodeGaji, Integer> tahun;
    public static volatile CollectionAttribute<PeriodeGaji, ListGaji_1> listGaji1Collection;
    public static volatile SingularAttribute<PeriodeGaji, String> bulan;
    public static volatile SingularAttribute<PeriodeGaji, Date> tanggalGajian;

}