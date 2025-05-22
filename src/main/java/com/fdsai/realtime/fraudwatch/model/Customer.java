package com.fdsai.realtime.fraudwatch.model;
import com.fdsai.realtime.fraudwatch.enums.Category;
import com.fdsai.realtime.fraudwatch.enums.Currency;
import com.fdsai.realtime.fraudwatch.enums.Merchant;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Random;

@Document(collection = "customers")
public class Customer {
    @Id
    private String id;
    private final String userId;
    private final List<Merchant> merchants; // Trusted merchants
    private final List<Category> categories; // Trusted categories
    private final Double meanSpending;
    private final Double spendingStdDev;
    private final Currency preferredCurrency;

    public Customer(String userId, List<Merchant> merchants, List<Category> categories,
                    Double meanSpending, Double spendingStdDev, Currency preferredCurrency) {
        this.userId = userId;
        this.merchants = merchants;
        this.categories = categories;
        this.meanSpending = meanSpending;
        this.spendingStdDev = spendingStdDev;
        this.preferredCurrency = preferredCurrency;
    }

    public String getId() { return id; }
    public String getUserId() { return userId; }
    public List<Merchant> getMerchants() { return merchants; }
    public List<Category> getCategories() { return categories; }
    public Double getMeanSpending() { return meanSpending; }
    public Double getSpendingStdDev() { return spendingStdDev; }
    public Currency getPreferredCurrency() { return preferredCurrency; }
    public Category getFrequentCategory() {
        Random random = new Random();
        return categories.get(random.nextInt(categories.size()));
    }
}