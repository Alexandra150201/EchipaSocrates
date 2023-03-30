package tasks.controller;

import org.junit.jupiter.api.*;
import tasks.model.Task;
import tasks.services.DateService;
import tasks.services.TasksService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class NewEditControllerTest {
    private DateService dateService=new DateService();
    private  Date startDateWithNoTime= new Date();
    private  NewEditController ctrl= new NewEditController();
    private Date di,di2,di3= new Date();
    private Date dou,dou2,dou3= new Date();

    @BeforeEach
    void setUp() {
        ctrl.setService(new TasksService(null));
        startDateWithNoTime = dateService.getDateValueFromLocalDate(LocalDate.now());
        di =dateService.getDateMergedWithTime("8:00", startDateWithNoTime);
        dou =dateService.getDateMergedWithTime("10:00", startDateWithNoTime);
        di2 =dateService.getDateMergedWithTime("13:00", startDateWithNoTime);
        dou2 =dateService.getDateMergedWithTime("15:00", startDateWithNoTime);
        di3 =dateService.getDateMergedWithTime("0:00", startDateWithNoTime);
        dou3 =dateService.getDateMergedWithTime("3:00", startDateWithNoTime);


    }

    @Disabled
    @AfterEach
    void tearDown() {
    }

    @Test
    @Order(1)
    @DisplayName("Test pt ECP valid")
    void makeTaskECPvalid() {

        Task ecpv = new Task("Mancare",di, dou,30);

        Task t = ctrl.makeTask("Mancare", LocalDate.now(),"8:00",LocalDate.now(),"10:00","0:30",true);

        assertEquals(t.getTitle(),ecpv.getTitle());

    }

    @Test
    @Order(2)
    void makeTaskECPinvalid1() {
          Task ecpi= new Task("Iesiredupamasaaceastaa",di2, dou2,30);
          Task t= null;

        try {
            t = ctrl.makeTask("Iesiredupamasaaceastaa", LocalDate.now(),"13:00",LocalDate.now(),"15:00","0:30",true);
            assertNotEquals(t.getTitle(),ecpi.getTitle());
        }
        catch (Exception e){
            assertEquals(t,null);
        }
    }

    @Test
    @Order(3)
    void makeTaskECPinvalid2() {
        Task ecpi = new Task("",di2, dou2,30);
        Task t= null;

        try {
            t = ctrl.makeTask("", LocalDate.now(),"-5:00",LocalDate.now(),"15:00","0:30",true);
            assertNotEquals(t.getTitle(),ecpi.getTitle());
        }
        catch (Exception e){
            assertEquals(t,null);
        }
    }

    @Test
    @Order(4)
    void makeTaskECPinvalid3() {
        Task ecpi = new Task("Da",di2, dou2,30);
        Task t= null;

        try {
            t = ctrl.makeTask("Da", LocalDate.now(),"34:00",LocalDate.now(),"23:00","0:30",true);
            assertNotEquals(t.getTitle(),ecpi.getTitle());
        }
        catch (Exception e){
            assertEquals(t,null);
        }
    }


    @Test
    @Order(5)
    void makeTaskBVAvalid1() {

        Task bvav = new Task("A",di3, dou3,30);

        Task t = ctrl.makeTask("A", LocalDate.now(),"0:00",LocalDate.now(),"10:00","0:30",true);

        assertEquals(t.getTitle(),bvav.getTitle());
    }

    @Test
    @Order(6)
    void makeTaskBVAvalid2() {

        Task bvav = new Task("Mainemergemacasasasa",di3, dou3,30);

        Task t = ctrl.makeTask("Mainemergemacasasasa", LocalDate.now(),"23:00",LocalDate.now(),"23:10","0:30",true);

        assertEquals(t.getTitle(),bvav.getTitle());
    }

    @Test
    @RepeatedTest(3)
    @Order(7)
    void makeTaskBVAinvalid1() {
        Task bvai = new Task("",di3, dou3,30);
        Task t= null;

        try {
            t = ctrl.makeTask("", LocalDate.now(),"-1:00",LocalDate.now(),"23:00","0:30",true);
            assertNotEquals(t.getTitle(),bvai.getTitle());
        }
        catch (Exception e){
            assertEquals(t,null);
        }
    }

    @Test
    @Order(8)
    void makeTaskBVAinvalid2() {
        Task bvai = new Task("Mainemergemacasasasas",di3, dou3,30);
        Task t= null;

        try {
            t = ctrl.makeTask("Mainemergemacasasasas", LocalDate.now(),"24:00",LocalDate.now(),"20:00","0:30",true);
            assertNotEquals(t.getTitle(),bvai.getTitle());
        }
        catch (Exception e){
            assertEquals(t,null);
        }
    }
    }