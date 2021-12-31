package onlineroomrent.service;

import onlineroomrent.dao.DaoService;
import onlineroomrent.dao.entity.AdminEntity;
import onlineroomrent.dateUtil.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SchedulerService {
    @Autowired
    private DaoService daoService;

    @PostConstruct
    public void display(){
        System.out.println("display method called post construct");
    }
   @Scheduled(cron = "0 30 4 * * ?")
    public void unBlockAccount(){
        List<AdminEntity> list=
        daoService.findAll().stream().filter(entity->{
            return entity.getIsBlock().equals(Boolean.TRUE);
        }).collect(Collectors.toList());

        list.stream().filter(entity->entity.getNonBlockDate().before(DateUtil.todayDate())).forEach(entity->{
            entity.setIsBlock(Boolean.FALSE);
            entity.setMaxAttempt(3);
            entity.setFailedAttempt(3);
            daoService.saveAdmin(entity);
        });

    }

}
