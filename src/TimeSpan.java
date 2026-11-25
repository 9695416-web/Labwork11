public class TimeSpan {
    private int hours;
    private int minutes;

    // Конструктор без аргументів (0 год 0 хв)
    public TimeSpan() {
        this.hours = 0;
        this.minutes = 0;
    }

    // Конструктор з 1 аргументом (хвилини)
    public TimeSpan(int minutes) {
        if (minutes < 0) {
            throw new IllegalInputTimeSpanException("Кількість хвилин не може бути від'ємною: " + minutes);
        }
        this.hours = minutes / 60;
        this.minutes = minutes % 60;
    }

    // Конструктор з 2 аргументами (години, хвилини)
    public TimeSpan(int hours, int minutes) {
        if (hours < 0 || minutes < 0 || minutes > 59) {
            throw new IllegalInputTimeSpanException(
                    "Некоректний інтервал часу: години = " + hours + ", хвилини = " + minutes
            );
        }
        this.hours = hours;
        this.minutes = minutes;
    }

    // Конструктор копіювання
    public TimeSpan(TimeSpan other) {
        if (other == null) {
            throw new IllegalInputTimeSpanException("Інтервал часу не може бути null");
        }
        this.hours = other.hours;
        this.minutes = other.minutes;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getTotalMinutes() {
        return hours * 60 + minutes;
    }

    public double getTotalHours() {
        return hours + minutes / 60.0;
    }

    // Додавання годин та хвилин
    public void add(int hours, int minutes) {
        if (hours < 0 || minutes < 0 || minutes > 59) {
            throw new IllegalInputTimeSpanException(
                    "Некоректні дані для додавання: години = " + hours + ", хвилини = " + minutes
            );
        }
        int totalMinutes = getTotalMinutes() + hours * 60 + minutes;
        this.hours = totalMinutes / 60;
        this.minutes = totalMinutes % 60;
    }

    // Додавання іншого TimeSpan
    public void add(TimeSpan span) {
        if (span == null) {
            throw new IllegalInputTimeSpanException("Інтервал часу не може бути null");
        }
        add(span.getHours(), span.getMinutes());
    }

    // Віднімання годин та хвилин
    public void subtract(int hours, int minutes) {
        if (hours < 0 || minutes < 0 || minutes > 59) {
            throw new IllegalInputTimeSpanException(
                    "Некоректні дані для віднімання: години = " + hours + ", хвилини = " + minutes
            );
        }
        int currentTotal = getTotalMinutes();
        int subtractTotal = hours * 60 + minutes;
        if (currentTotal - subtractTotal < 0) {
            throw new NegativeTimeSpanException("Результат віднімання дає від'ємний інтервал часу");
        }
        int result = currentTotal - subtractTotal;
        this.hours = result / 60;
        this.minutes = result % 60;
    }

    // Віднімання іншого TimeSpan
    public void subtract(TimeSpan span) {
        if (span == null) {
            throw new IllegalInputTimeSpanException("Інтервал часу не може бути null");
        }
        subtract(span.getHours(), span.getMinutes());
    }

    // Масштабування інтервалу
    public void scale(int factor) {
        if (factor <= 0) {
            throw new IllegalScaleFactorException("Множник масштабування має бути більшим за 0: " + factor);
        }
        int totalMinutes = getTotalMinutes() * factor;
        this.hours = totalMinutes / 60;
        this.minutes = totalMinutes % 60;
    }

    @Override
    public String toString() {
        return hours + "h " + minutes + "m";
    }
}