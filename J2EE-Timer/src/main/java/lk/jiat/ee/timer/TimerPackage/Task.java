package lk.jiat.ee.timer.TimerPackage;

import java.io.Serializable;

public class Task implements Serializable {
    public String getTaskName() {
        return taskName;
    }

    public String getTaskId() {
        return taskId;
    }

    private final String taskId;
    private final String taskName;

    public Task(String taskId, String taskName) {
        this.taskId = taskId;
        this.taskName = taskName;
    }
}
