package tests;

public enum PurchasePlanStatus {
    DF_APPROVED(1, "На согласовании ДФ"),
    GRBS_APPROVED(2, "На согласовании ГРБС"),
    APPROVED(3, "Согласовано"),
    CORRECTION(4, "К корректировке"),
    SIGNED(5, "Утверждён"),
    SENT_FOR_PUBLICATION(6, "Отправлен на публикацию"),
    PUBLICATION_ERROR(7, "Ошибка публикации"),
    PUBLISHED(8, "Опубликован"),
    REMOVED(9, "Удалён"),
    ON_FO_APPROVAL(10, "На согласовании ФО");

    private int id;
    private String name;

    PurchasePlanStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
