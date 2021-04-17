package Entity.Pack;

import Entity.Client.Client;

/**
 * In questa classe ho usato un builder per la costruzione di un collo
 * nello specifico ho usato l'implementazione del builder di  Joshua Bloch del  libro Effective Java
 * una soluzione un po' diversa alla classica in quanto non necessariamente abbiamo bisogno di un director ma semplicemente possiamo
 * creare l'istanza del pack direttamente andano a richiamare questa classe ed andare a settare i valori necessari questo evita la molteplice costruzioni
 * di costruttori e utilizzo di null qual'ora un oggetto complesso  con tanti capi da riempire non dovesse settare uno dei campi fornendo al programmatore un
 * utilizzo piu completo e pulito
 */
public final class PackBuilder {

    private int id;
    private int weight;
    private PackState packState;
    private String idSender;
    private String idRecipient;
    private String addressSender;
    private String telephoneSender;
    private String citySender;
    private String nameSender;
    private String surnameSender;
    private String addressRecipient;
    private String telephoneRecipient;
    private String cityRecipient;
    private String nameRecipient;
    private String surnameRecipient;

    private PackBuilder() {

    }


    public static PackBuilder newBuilder() {
        return new PackBuilder();
    }

    public PackBuilder id(int id) {
        this.id = id;

        return this;
    }

    public PackBuilder weight(int weight) {
        this.weight = weight;
        return this;
    }

    public PackBuilder PackStateWithInt(int packState) {
        if (packState == 0)
            this.packState = PackState.PACK_INSERTED;
        else if (packState == 1)
            this.packState = PackState.PACK_DELIVERING;
        else if (packState == 2)
            this.packState = PackState.PACK_DELIVERED;

        return this;
    }

    public PackBuilder PackState(PackState packState) {
        this.packState = packState;
        return this;
    }

    public PackBuilder addressSender(String addressSender) {
        this.addressSender = addressSender;
        return this;
    }

    public PackBuilder telephoneSender(String telephoneSender) {
        this.telephoneSender = telephoneSender;
        return this;
    }

    public PackBuilder citySender(String citySender) {
        this.citySender = citySender;
        return this;
    }

    public PackBuilder nameSender(String nameSender) {
        this.nameSender = nameSender;
        return this;
    }

    public PackBuilder surnameSender(String surnameSender) {
        this.surnameSender = surnameSender;
        return this;
    }

    public PackBuilder addressRecipient(String addressRecipient) {
        this.addressRecipient = addressRecipient;
        return this;
    }


    public PackBuilder telephoneRecipient(String telephoneRecipient) {
        this.telephoneRecipient = telephoneRecipient;
        return this;
    }

    public PackBuilder cityRecipient(String cityRecipient) {
        this.cityRecipient = cityRecipient;
        return this;
    }

    public PackBuilder nameRecipient(String nameRecipient) {
        this.nameRecipient = nameRecipient;
        return this;
    }

    public PackBuilder surnameRecipient(String surnameRecipient) {
        this.surnameRecipient = surnameRecipient;
        return this;
    }

    public PackBuilder idRecipient(String idRecipient) {
        this.idRecipient = idRecipient;
        return this;
    }

    public PackBuilder idSender(String idSender) {
        this.idSender = idSender;
        return this;
    }

    /**
     * Il metodo build va semplicemte a costruire per intero l'oggetto pack qualsiasi valore noi avremmmo settato o meno
     */
    public Pack build() {
        return new Pack(id, weight, packState,
                new Client(addressSender, telephoneSender, citySender, nameSender, surnameSender),
                new Client(addressRecipient, telephoneRecipient, cityRecipient, nameRecipient, surnameRecipient));
    }

}
