package com.fdsai.realtime.fraudwatch.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fdsai.realtime.fraudwatch.enums.Category;
import com.fdsai.realtime.fraudwatch.enums.Currency;
import com.fdsai.realtime.fraudwatch.enums.Merchant;
import org.bson.BsonDocument;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;
import java.util.Random;
import java.util.UUID;

import static com.fdsai.realtime.fraudwatch.enums.Merchant.getRandomMerchant;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "transactions")
public class Transaction {

    @Id
    private String id;

    @JsonProperty("transaction_id")
    private String transactionId;

    private String userId;
    private double amount;
    private Currency currency;
    private Instant timestamp;
    private Merchant merchant;
    private Category category;
    private boolean isFraud;
    private float[] embedding = new float[]{};

    public Transaction() {}

    public Transaction(String id, String transactionId, String userId, double amount, Currency currency,
                       Instant timestamp, Merchant merchant, Category category, boolean isFraud) {
        this.id = id;
        this.transactionId = transactionId;
        this.userId = userId;
        this.amount = amount;
        this.currency = currency;
        this.timestamp = timestamp;
        this.merchant = merchant;
        this.category = category;
        this.isFraud = isFraud;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isFraud() {
        return isFraud;
    }

    public void setFraud(boolean fraud) {
        isFraud = fraud;
    }

    public float[] getEmbedding() {
        return embedding;
    }

    public void setEmbedding(float[] embedding) {
        this.embedding = embedding;
    }

    /**
     * Generates a text representation for embedding models.
     */
    public String generateEmbeddingText() {
        return userId + " " + amount + " " + currency + " " + merchant + " " + category;
    }

    /**
     * Generate a synthetic random transaction for a given customer profile.
     */
    public static Transaction generateRandom(Customer customer) {
        Random random = new Random();
        boolean isSuspicious = random.nextDouble() < 0.1;

        double amount = isSuspicious
                ? customer.getMeanSpending() * (2.0 + random.nextDouble())
                : customer.getMeanSpending() * (0.5 + random.nextDouble());

        Category category = isSuspicious
                ? customer.getUnfrequentCategory()
                : customer.getFrequentCategory();

        Merchant merchant = getRandomMerchant(category);

        Currency currency = isSuspicious
                ? customer.getRandomSuspiciousCurrency()
                : customer.getPreferredCurrency();

        return new Transaction(
                null,
                UUID.randomUUID().toString(),
                customer.getUserId(),
                amount,
                currency,
                Instant.now(),
                merchant,
                category,
                false // actual fraud detection will override this
        );
    }
}
