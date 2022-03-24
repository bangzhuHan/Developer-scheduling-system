package domain;

import service.Status;

import static service.Status.FREE;

public class Programmer extends Employee{
    private int memberId;//记录成员加入团队后的id
    private Status status = FREE;
    private Equipment equipment;

    public Programmer() {
    }

    public Programmer(int id, String name, int age, double salary, Equipment equipment) {
        super(id, name, age, salary);
        this.equipment = equipment;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

   //3	李彦宏	23	7000.0	程序员	FREE			戴尔(NEC17寸)
    @Override
    public String toString() {
        return getDetails() + "\t" + "程序员" + "\t" + status + "\t" + equipment.getDescription();
    }

    // 1/6	任志强	22	6800.0	程序员
    public String getBaseForTeam(){
        return getMemberId() + "/" +getDetails() + "\t";
    }

    public String getDetailsForTeam(){
        return getBaseForTeam() +  "程序员\n";
    }

}
