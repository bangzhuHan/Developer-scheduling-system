package view;

import domain.Employee;
import domain.Programmer;
import service.NameListService;
import service.TeamException;
import service.TeamService;

public class TeamView {
    private NameListService listSvc = new NameListService();
    private  TeamService teamSvc = new TeamService();

    public void enterMainMenu(){

        boolean loopFlag = true;
        char menu = 0;
        while(loopFlag){
            if(menu != '1')
                listAllEmployees();

            System.out.println("1-团队列表 2-添加团队成员 3-删除团队成员 4-退出 请选择(1-4):");
            menu = TSUtility.readMenuSelection();
            switch (menu){
                case '1':
                    listTeam();
                    break;
                case '2':
                    addMember();
                    break;
                case '3':
                    deleteMember();
                    break;
                case '4':
                    System.out.println("是否确认退出(Y/N):");
                    char ch = TSUtility.readConfirmSelection();
                    if('Y' == ch)
                        loopFlag = false;
                    break;
            }
        }
    }

    public void listAllEmployees(){
//        System.out.println("显示全部成员信息");
        System.out.println("--------------------------开发人员调度软件--------------------------");

        Employee[] employees = listSvc.getAllEmployees();
        if(employees.length == 0 || employees == null){
            System.out.println("无法找到团队");
        }else{
            System.out.println("ID\t姓名\t年龄\t工资\t职位\t状态\t奖金\t股票\t领用设备");
        }
        for(Employee e: employees)
            System.out.println(" " + e);
        System.out.println("-------------------------------------------------------------------------------");
    }

    public void listTeam(){
        //System.out.println("展示开发团队情况");
        System.out.println("\n--------------------团队成员列表---------------------\n");
        Programmer[] team = teamSvc.getTeam();
        if(team.length == 0) System.out.println("开发团队目前没有成员");
            else System.out.println("TID/ID\t姓名\t年龄\t工资\t职位\t奖金\t股票");
        for (Programmer p : team) {
            System.out.println(" " + p.getDetailsForTeam());
        }
        System.out.println("------------------------------------");
    }
    /*private void listTeam() {
        System.out.println("\n--------------------团队成员列表---------------------\n");
        Programmer[] team = teamSvc.getTeam();
        if (team.length == 0) {
            System.out.println("开发团队目前没有成员！");
        } else {
            System.out.println("TID/ID\t姓名\t年龄\t工资\t职位\t奖金\t股票");
        }

        for (Programmer p : team) {
            System.out.println(" " + p.getDetailsForTeam());
        }
        System.out.println("-----------------------------------------------------");
    }*/


    public void addMember(){
        //System.out.println("添加团队成员");
        System.out.println("---------------------添加成员---------------------");
        System.out.print("请输入要添加的员工ID：");
        int  id = TSUtility.readInt();

        try {
            Employee e = listSvc.getEmployee(id);
            teamSvc.addMember(e);
            System.out.println("添加成功");
        } catch (TeamException ex) {
            System.out.println("添加失败，原因：" + ex.getMessage());
        }

        // 按回车键继续...
        TSUtility.readReturn();
    }

    public void deleteMember(){
        //System.out.println("删除团队成员");
        System.out.println("---------------------删除成员---------------------");
        System.out.print("请输入要删除员工的TID：");
        int id = TSUtility.readInt();
        System.out.println("确认是否删除(Y/N)：");
        char ys = TSUtility.readConfirmSelection();

        if(ys == 'N')   return;

        try {
            teamSvc.removeMember(id);
            System.out.println("删除成功！");
        } catch (TeamException e) {
            System.out.println("删除失败，原因：" + e.getMessage());
        }

        // 按回车键继续...
        TSUtility.readReturn();

    }

    public static void main(String[] args) {
        TeamView view = new TeamView();
        view.enterMainMenu();
    }

}
