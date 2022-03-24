package service;

import domain.Architect;
import domain.Designer;
import domain.Employee;
import domain.Programmer;

/**
 * 功能：关于开发团队成员的管理：添加、删除等。
 */
public class TeamService {
    private int counter = 1;//小组成员团队ID
    private final int MAX_MEMBER = 5;//开发团队最多人数
    private int total = 0;//当前总人数
    private Programmer[] team = new Programmer[MAX_MEMBER];//用于存储开发人员信息的数组

    //返回team中所有程序员构成的数组
    public Programmer[] getTeam(){
        Programmer[] team1 = new Programmer[total];
        for (int i = 0; i < total; i++) {
            team1[i] = team[i];
        }
        return team1;
    }

    public void addMember(Employee e) throws TeamException {
        if (total >= MAX_MEMBER)
            throw new TeamException("成员已满，无法添加");
        if (!(e instanceof Programmer))
            throw new TeamException("该成员不是开发人员，无法添加");


        Programmer p = (Programmer) e;
        if (isExit(p)) {
            throw new TeamException("该成员已在团队中");
        }
        if("BUSY".equals(p.getStatus().getName())){
            throw new TeamException("该员工已是该团队成员");
        } else if("VOCATION".equals(p.getStatus().getName())){
            throw new TeamException("该员工正在休假，无法添加");
        }

        int numOfArch = 0, numOfDsgn = 0, numOfPrg = 0;
        for (int i = 0; i < total; i++) {
            if(team[i] instanceof Architect)    numOfArch++;
                else if(team[i] instanceof Designer) numOfDsgn++;
                    else numOfPrg++;
        }

        if(p instanceof Architect){
            if(numOfArch >= 1) throw new TeamException("团队中至多只能有一名架构师");
        }else if(p instanceof Designer){
            if(numOfDsgn >= 2) throw new TeamException("团队中至多只能有两名设计师");
        }else if(p instanceof Programmer){
            if(numOfPrg >= 3) throw new TeamException("团队中至多只能有三名程序员");
        }

        //添加到数组,先后顺序无所谓
        p.setStatus(Status.BUSY);
        p.setMemberId(counter++);
        team[total++] = p;
    }

    private boolean isExit (Programmer p){
        for (int i = 0; i < total; i++) {
            if (team[i].getId() == p.getId()) return true;
        }
        return false;
    }

    //删除指定位置的程序员
    public  void removeMember(int memberId) throws TeamException {
        int i = 0;
        for(;i < total; i++){
            if(team[i].getMemberId() == memberId) {
                team[i].setStatus(Status.FREE);
                break;
            }
        }

        //遍历一遍找不到报异常
        if(i == total)
            throw new TeamException("找不到成员，无法删除");
        //覆盖
        for(int j = i + 1; j < total; j++){
            team[j - 1] = team[j];
        }
        team[--total] = null;


    }


}
