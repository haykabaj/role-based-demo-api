package am.hayk.abajyan.rolebaseddemoapi.constants;

public enum TaskStatus {

    NEW_TASK("new-task"), BUG("bug"), IN_PROCESS("in-process"), REOPEN("reopen"), RESOLVED("resolved"), DONE("done");

    private String taskStatus;

     TaskStatus(String taskStatus){
        this.taskStatus = taskStatus;
    }

    public String getTaskStatus() {
        return taskStatus;
    }
}
