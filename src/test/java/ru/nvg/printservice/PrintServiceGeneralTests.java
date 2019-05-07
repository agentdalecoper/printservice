package ru.nvg.printservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.nvg.printservice.controllers.JobController;
import ru.nvg.printservice.controllers.StatisticController;
import ru.nvg.printservice.dao.DeviceRepository;
import ru.nvg.printservice.dao.JobRepository;
import ru.nvg.printservice.dao.UserRepository;
import ru.nvg.printservice.domain.Device;
import ru.nvg.printservice.domain.Job;
import ru.nvg.printservice.domain.JobType;
import ru.nvg.printservice.domain.User;
import ru.nvg.printservice.dto.JobDto;
import ru.nvg.printservice.dto.JobSummaryDto;
import ru.nvg.printservice.dto.SaveJobCmd;
import ru.nvg.printservice.dto.StatisticSummaryNodeDto;
import ru.nvg.printservice.services.JobService;
import ru.nvg.printservice.services.StatisticsService;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
@EnableWebMvc
public class PrintServiceGeneralTests {
    @LocalServerPort
    private int port;
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    private final String JOBS_URL = JobController.BASE_URL;
    private final String STATISTIC_URL = StatisticController.BASE_URL;

    @Autowired
    JobService jobService;
    @Autowired
    StatisticsService statisticsService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JobRepository jobRepository;
    @Autowired
    DeviceRepository deviceRepository;

    @Before
    public void setup() {
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    /*
    Test creating the job
     */
    @Test
    @Rollback
    public void TestCreateJob() throws AssertionError, Exception {
        String user1Name = "userTest_1";
        String device1Name = "deviceTest_1";

        User user1 = createUser(user1Name);
        Device device1 = createDevice(device1Name);

        //Create job
        JobDto job1 = createJobDto(999L, user1.getName(),
                device1.getName(), JobType.print, 99);

        //Pass job to command and pass to the service
        SaveJobCmd cmd = new SaveJobCmd();
        cmd.jobs = new ArrayList<>();
        cmd.jobs.add(job1);

        JobSummaryDto res = jobService.saveJobs(cmd);
        assert res.summaryForTransaction.containsKey(user1Name);
        assert res.summaryForTransaction.get(user1Name) == 99;
        assert jobRepository.findById(999L).isPresent();
    }

    /*
    Test create multiple jobs with different users
     */
    @Test
    @Rollback
    public void TestMultipleUsersJobCreation() throws AssertionError, Exception {
        User user1 = createUser();
        User user2 = createUser();
        User user3 = createUser();
        User user4 = createUser();

        Device device1 = createDevice();
        Device device2 = createDevice();

        //Create jobs
        JobDto job1 = createJobDto(999L, user1.getName(),
                device1.getName(), JobType.print, 2);

        JobDto job2 = createJobDto(1000L, user1.getName(),
                device2.getName(), JobType.fax, 4);

        JobDto job3 = createJobDto(1001L, user1.getName(),
                device2.getName(), JobType.scan, 6);

        JobDto job4 = createJobDto(1002L, user2.getName(),
                device1.getName(), JobType.copy, 99);


        //Create bulk job command and pass it to the service
        SaveJobCmd cmd = new SaveJobCmd();
        cmd.jobs = new ArrayList<>();
        cmd.jobs.add(job1);
        cmd.jobs.add(job2);
        cmd.jobs.add(job3);
        cmd.jobs.add(job4);

        JobSummaryDto res = jobService.saveJobs(cmd);

        Iterable<StatisticSummaryNodeDto> lst = getStatistic(user2);
    }


    /*
    Test check statistic search in different variations
     */
    @Test
    @Rollback
    public void TestStatistic() throws AssertionError, Exception {
        User user1 = createUser();
        User user2 = createUser();
        User user3 = createUser();
        User user4 = createUser();

        Device device1 = createDevice();
        Device device2 = createDevice();

        //Create jobs
        JobDto job1 = createJobDto(999L, user1.getName(),
                device1.getName(), JobType.print, 2);

        JobDto job2 = createJobDto(1000L, user1.getName(),
                device2.getName(), JobType.fax, 4);

        JobDto job3 = createJobDto(1001L, user2.getName(),
                device2.getName(), JobType.scan, 6);

        JobDto job4 = createJobDto(1002L, user3.getName(),
                device1.getName(), JobType.copy, 99);

        //Create bulk job command and pass it to the service
        SaveJobCmd cmd = new SaveJobCmd();
        cmd.jobs = new ArrayList<>();
        cmd.jobs.add(job1);
        cmd.jobs.add(job2);
        cmd.jobs.add(job3);
        cmd.jobs.add(job4);

        jobService.saveJobs(cmd);

        //Check statistic
        List<StatisticSummaryNodeDto> res = getStatistic(user1);
        assert res.size() == 2;
        assert res.get(0).getJobId() == 999L;
        assert res.get(0).getAmount() == 2;
        assert res.get(1).getJobId() == 1000L;
        assert res.get(1).getJobType() == JobType.fax;

        res = getStatistic(user2);
        assert res.size() == 1;
        assert res.get(0).getJobId() == 1001L;

        res = getStatistic(JobType.copy);
        assert res.size() == 1;
        assert res.get(0).getJobId() == 1002L;
        assert res.get(0).getAmount() == 99;
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    //Handle exceptions correctly
    private ResultActions SaveJobs(SaveJobCmd cmd) throws AssertionError, Exception {
        HttpEntity<SaveJobCmd> request = new HttpEntity<>(cmd, headers);

        ResultActions response =
                this.mockMvc.perform(post(JOBS_URL)
                        .contentType(MediaType.APPLICATION_XML)
                        .content(saveJobToXml(cmd)))
                        .andExpect(status().isOk());

        return response;
    }

    private static String saveJobToXml(SaveJobCmd jobDto) {
        String xmlString = "";
        try {
            JAXBContext context = JAXBContext.newInstance(SaveJobCmd.class);
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

            StringWriter sw = new StringWriter();
            m.marshal(jobDto, sw);
            xmlString = sw.toString();

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return xmlString;
    }

    private User createUser(String name) {
        User user = new User();
        user.setName(name);

        return userRepository.save(user);
    }

    private Device createDevice(String name) {
        Device device = new Device();
        device.setName(name);

        return deviceRepository.save(device);
    }

    private User createUser() {
        String generatedString = RandomStringUtils.randomAlphabetic(10);
        return createUser(generatedString);
    }

    private Device createDevice() {
        String generatedString = RandomStringUtils.randomAlphabetic(10);
        return createDevice(generatedString);
    }

    private JobDto createJobDto(Long id, String user, String device, JobType type, int amount) {
        JobDto job = new JobDto();
        job.setId(id);
        job.setUserName(user);
        job.setDeviceName(device);
        job.setType(type);
        job.setAmount(amount);
        return job;
    }

    private List<StatisticSummaryNodeDto> getStatistic(User user) {
        Iterable<Job> sum = statisticsService.statisticsFilter(user.getName(),
                null, null, null, null);

        return statisticsService.toSummary(sum);
    }

    private List<StatisticSummaryNodeDto> getStatistic(User user, Device device) {
        Iterable<Job> sum = statisticsService.statisticsFilter(user.getName(),
                device.getName(), null, null, null);

        return statisticsService.toSummary(sum);
    }

    private List<StatisticSummaryNodeDto> getStatistic(JobType jobType) {
        Iterable<Job> sum = statisticsService.statisticsFilter(null,
                null, jobType, null, null);

        return statisticsService.toSummary(sum);
    }

    private List<StatisticSummaryNodeDto> getStatistic(LocalDateTime t1, LocalDateTime t2) {
        Iterable<Job> sum = statisticsService.statisticsFilter(null,
                null, null, t1, t2);

        return statisticsService.toSummary(sum);
    }
}

