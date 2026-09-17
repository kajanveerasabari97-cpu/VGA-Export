package com.example.data.local

import com.example.data.model.ProductCategory
import com.example.data.model.ProductEntity

object InitialData {
    fun getDefaultProducts(): List<ProductEntity> {
        var order = 0
        return listOf(
            // --- VEGETABLES ---
            ProductEntity(
                name = "Small Onion (Shallots)",
                category = ProductCategory.VEGETABLES.displayName,
                description = "Export-quality premium small shallot onions, dried and graded. Pungent aroma, firm skin, and long shelf life.",
                packingDetails = "5 kg / 10 kg Mesh Bags, Ventilated Crates",
                isAvailable = true,
                imageType = "veg",
                variety = "Bellary & Podi Onion Grade-A",
                minOrder = "1000 Kg",
                sortOrder = order++
            ),
            ProductEntity(
                name = "Ladies Finger / Okra",
                category = ProductCategory.VEGETABLES.displayName,
                description = "Tender, fresh, bright green export okra. Plucked young for minimal fiber, sorted for uniform length and crispness.",
                packingDetails = "5 kg Corrugated Fiberboard Boxes with aeration holes",
                isAvailable = true,
                imageType = "veg",
                variety = "Emerald Hybrid Grade-A",
                minOrder = "500 Kg",
                sortOrder = order++
            ),
            ProductEntity(
                name = "Fresh Coconut",
                category = ProductCategory.VEGETABLES.displayName,
                description = "Mature dehusked and semi-husked coconuts with rich water content and thick kernel. Free from blemishes.",
                packingDetails = "25 / 50 pieces per PP Gunny Bag or Carton",
                isAvailable = true,
                imageType = "veg",
                variety = "Pollachi Dehusked",
                minOrder = "1 Container / 2000 Pcs",
                sortOrder = order++
            ),
            ProductEntity(
                name = "Cucumber",
                category = ProductCategory.VEGETABLES.displayName,
                description = "Crisp, farm-fresh green cucumbers, straight and uniform. Retains hydration during temperature-controlled transit.",
                packingDetails = "5 kg / 10 kg Plastic Crates / Export Boxes",
                isAvailable = true,
                imageType = "veg",
                variety = "Salad & Slicing Cucumbers",
                minOrder = "500 Kg",
                sortOrder = order++
            ),
            ProductEntity(
                name = "Nookal / Kohlrabi",
                category = ProductCategory.VEGETABLES.displayName,
                description = "Freshly harvested pale green kohlrabi bulbs. Sweet, crisp flavor, trimmed and washed under strict hygiene.",
                packingDetails = "7 kg / 10 kg Export Cartons",
                isAvailable = true,
                imageType = "veg",
                variety = "White Vienna / Green Kohlrabi",
                minOrder = "500 Kg",
                sortOrder = order++
            ),
            ProductEntity(
                name = "Turnip",
                category = ProductCategory.VEGETABLES.displayName,
                description = "Clean, firm, purple-top white turnips. Firm roots, sweet peppery taste, suitable for air and sea export.",
                packingDetails = "10 kg Corrugated Boxes with perforated liner",
                isAvailable = true,
                imageType = "veg",
                variety = "Purple Top White Globe",
                minOrder = "500 Kg",
                sortOrder = order++
            ),
            ProductEntity(
                name = "Bajji Chilli",
                category = ProductCategory.VEGETABLES.displayName,
                description = "Thick-fleshed, mild heat light green bajji chillies. Uniform size, glossy skin, perfect for culinary use.",
                packingDetails = "4 kg / 5 kg Aerated Export Cartons",
                isAvailable = true,
                imageType = "veg",
                variety = "Bhavnagri Mild Chilli",
                minOrder = "300 Kg",
                sortOrder = order++
            ),
            ProductEntity(
                name = "Green Chilli",
                category = ProductCategory.VEGETABLES.displayName,
                description = "Spicy, fresh dark green chillies with intact stalks. Handpicked to maintain pungency and extended freshness.",
                packingDetails = "3 kg / 5 kg Thermocol or CFB Boxes",
                isAvailable = true,
                imageType = "veg",
                variety = "G4 / Teja Green Chilli",
                minOrder = "500 Kg",
                sortOrder = order++
            ),
            ProductEntity(
                name = "Bullet Chilli",
                category = ProductCategory.VEGETABLES.displayName,
                description = "Short, conical, high-pungency bullet chillies. Firm texture, deep green sheen, highly requested internationally.",
                packingDetails = "3 kg / 5 kg Ventilated Boxes",
                isAvailable = true,
                imageType = "veg",
                variety = "Indian Bullet Hot",
                minOrder = "300 Kg",
                sortOrder = order++
            ),
            ProductEntity(
                name = "Cabbage",
                category = ProductCategory.VEGETABLES.displayName,
                description = "Tight, compact, round green cabbages with outer protective wrapper leaves. High density and long shelf stability.",
                packingDetails = "15 kg / 20 kg Mesh Bags or Export Crates",
                isAvailable = true,
                imageType = "veg",
                variety = "Golden Acre / Green Express",
                minOrder = "1000 Kg",
                sortOrder = order++
            ),
            ProductEntity(
                name = "Fresh Drumstick (Moringa)",
                category = ProductCategory.VEGETABLES.displayName,
                description = "Slender, fleshy, vibrant green moringa drumsticks. Harvested before seed hardening for tender culinary texture.",
                packingDetails = "5 kg Corrugated Boxes (cut to uniform length)",
                isAvailable = true,
                imageType = "veg",
                variety = "PKM-1 / ODC Hybrid",
                minOrder = "400 Kg",
                sortOrder = order++
            ),

            // --- FRUITS ---
            ProductEntity(
                name = "Raw Mango",
                category = ProductCategory.FRUITS.displayName,
                description = "Firm, sour, crisp raw mangoes for culinary, pickling, and processing needs. Harvested at optimal maturity.",
                packingDetails = "10 kg / 15 kg Export Cartons with paper cushioning",
                isAvailable = true,
                imageType = "fruit",
                variety = "Totapuri, Kili Mooku & Totapuri Raw",
                minOrder = "1000 Kg",
                sortOrder = order++
            ),
            ProductEntity(
                name = "All Varieties of Banana",
                category = ProductCategory.FRUITS.displayName,
                description = "Premium export-grade bananas harvested at calibration grade. Thick skin, spotless peel, uniform bunch fingers.",
                packingDetails = "7 kg / 13 kg Vacuum Packed CFB Boxes",
                isAvailable = true,
                imageType = "fruit",
                variety = "Grand Naine (G9), Robusta, Red Banana, Poovan, Elakki",
                minOrder = "1 Reefer Container / 1000 Kg",
                sortOrder = order++
            ),
            ProductEntity(
                name = "Raspuri Mango",
                category = ProductCategory.FRUITS.displayName,
                description = "Aromatic sweet table mango known as the sweet jewel of South India. Juicy fiber-free golden pulp.",
                packingDetails = "3 kg / 5 kg Presentation Cartons with foam netting",
                isAvailable = true,
                imageType = "fruit",
                variety = "Raspuri Export Premium",
                minOrder = "500 Kg",
                sortOrder = order++
            ),
            ProductEntity(
                name = "Neelam Mango",
                category = ProductCategory.FRUITS.displayName,
                description = "Late-season favorite with smooth orange skin, distinct floral scent, and superb traveling tolerance.",
                packingDetails = "3 kg / 5 kg Export Packs with individual paper wraps",
                isAvailable = true,
                imageType = "fruit",
                variety = "Salem Neelam Grade-A",
                minOrder = "500 Kg",
                sortOrder = order++
            ),
            ProductEntity(
                name = "Sendura Mango",
                category = ProductCategory.FRUITS.displayName,
                description = "Distinctive honeyed sweetness with an attractive reddish-pink blush on golden peel. High pulp content.",
                packingDetails = "3 kg / 5 kg Export Cartons",
                isAvailable = true,
                imageType = "fruit",
                variety = "Sendura (Sindhooram)",
                minOrder = "500 Kg",
                sortOrder = order++
            ),
            ProductEntity(
                name = "Chikoo / Sapota",
                category = ProductCategory.FRUITS.displayName,
                description = "Sweet brown sapodilla with malty brown sugar richness. Plucked at green-mature stage for safe international air transport.",
                packingDetails = "5 kg Foam-Cushioned Export Boxes",
                isAvailable = true,
                imageType = "fruit",
                variety = "Cricket Ball & Oval Sapota",
                minOrder = "500 Kg",
                sortOrder = order++
            ),
            ProductEntity(
                name = "Pomegranate / Anar",
                category = ProductCategory.FRUITS.displayName,
                description = "Deep ruby red arils, sweet taste with soft seeds. Glossy outer rind, graded meticulously for export.",
                packingDetails = "3.5 kg / 4 kg Pre-formed Cartons (9-12 counts)",
                isAvailable = true,
                imageType = "fruit",
                variety = "Bhagwa / Ruby Red Anar",
                minOrder = "1000 Kg",
                sortOrder = order++
            ),
            ProductEntity(
                name = "Custard Apple (Seetha Pazham)",
                category = ProductCategory.FRUITS.displayName,
                description = "Segmented sweet fruit with creamy, aromatic white pulp. Handpicked with care to prevent bruising in flight.",
                packingDetails = "4 kg Cell-Pack Cartons",
                isAvailable = true,
                imageType = "fruit",
                variety = "Balanagar & Golden Custard Apple",
                minOrder = "300 Kg",
                sortOrder = order++
            ),
            ProductEntity(
                name = "Fresh Papaya",
                category = ProductCategory.FRUITS.displayName,
                description = "Semi-ripe export papayas with sweet, vibrant red-orange flesh. Firm skin suitable for air and sea transport.",
                packingDetails = "6 kg / 8 kg Foam Sleeve Cartons",
                isAvailable = true,
                imageType = "fruit",
                variety = "Red Lady 786",
                minOrder = "500 Kg",
                sortOrder = order++
            ),

            // --- LEAFY GREENS / KEERAI ---
            ProductEntity(
                name = "Siru Keerai",
                category = ProductCategory.LEAFY_GREENS.displayName,
                description = "Tender young amaranth greens. Rich in minerals, carefully harvested early morning, washed, bundled, and chilled.",
                packingDetails = "Air freight chilled thermocol boxes with ice gel packs",
                isAvailable = true,
                imageType = "greens",
                variety = "Amaranthus tricolor (Tamil Nadu Farm-Fresh)",
                minOrder = "150 Kg",
                sortOrder = order++
            ),
            ProductEntity(
                name = "Arai Keerai",
                category = ProductCategory.LEAFY_GREENS.displayName,
                description = "Traditional green amaranth with soft, nutritious leaves. Trimmed root systems, moisture-sealed for freshness.",
                packingDetails = "Chilled Air Cargo Crates with moist breathable paper wrap",
                isAvailable = true,
                imageType = "greens",
                variety = "Traditional Arai Keerai",
                minOrder = "150 Kg",
                sortOrder = order++
            ),
            ProductEntity(
                name = "Palak / Spinach",
                category = ProductCategory.LEAFY_GREENS.displayName,
                description = "Crisp, broad, dark green spinach leaves without stems blemishes. Cleaned and pre-cooled for maximum shelf vitality.",
                packingDetails = "5 kg Ventilated Styrofoam Boxes with Gel Coolers",
                isAvailable = true,
                imageType = "greens",
                variety = "All Green / Pusa Palak",
                minOrder = "200 Kg",
                sortOrder = order++
            ),
            ProductEntity(
                name = "Methi / Fenugreek Greens",
                category = ProductCategory.LEAFY_GREENS.displayName,
                description = "Aromatic baby fenugreek leaves. Mild bittersweet flavor, tender stems, sorted free of soil and yellowed leaves.",
                packingDetails = "Chilled Aerated Cartons (200g / 500g bunch packs)",
                isAvailable = true,
                imageType = "greens",
                variety = "Kasuri & Regular Indian Methi",
                minOrder = "150 Kg",
                sortOrder = order++
            ),
            ProductEntity(
                name = "Murungai Keerai (Moringa Leaves)",
                category = ProductCategory.LEAFY_GREENS.displayName,
                description = "High-demand nutrient powerhouse drumstick leaves. Fresh green sprigs, carefully stripped or bunched for international delivery.",
                packingDetails = "Pre-cooled insulated cartons with temperature indicators",
                isAvailable = true,
                imageType = "greens",
                variety = "Fresh Export Moringa Foliage",
                minOrder = "150 Kg",
                sortOrder = order++
            ),
            ProductEntity(
                name = "Fresh Coriander / Kothamalli",
                category = ProductCategory.LEAFY_GREENS.displayName,
                description = "Intensely fragrant cilantro with healthy green root-trimmed stems. Vacuum-cooled and packaged for overseas markets.",
                packingDetails = "5 kg Chilled insulated boxes with ice packs",
                isAvailable = true,
                imageType = "greens",
                variety = "Indian Super Fragrant Cilantro",
                minOrder = "200 Kg",
                sortOrder = order++
            ),
            ProductEntity(
                name = "Fresh Mint / Pudina",
                category = ProductCategory.LEAFY_GREENS.displayName,
                description = "Vibrant aromatic spearmint leaves. Thick, essential-oil rich leaves, harvested at peak aroma.",
                packingDetails = "5 kg Chilled Foam Boxes with hydration liners",
                isAvailable = true,
                imageType = "greens",
                variety = "Dark Green Spearmint",
                minOrder = "150 Kg",
                sortOrder = order++
            ),
            ProductEntity(
                name = "Fresh Curry Leaves (Karuveppilai)",
                category = ProductCategory.LEAFY_GREENS.displayName,
                description = "Essential South Indian culinary herb. Intensely aromatic dark green leaves, pest-free, vacuum-graded for air shipping.",
                packingDetails = "5 kg / 10 kg Export Cartons with micro-perforated bags",
                isAvailable = true,
                imageType = "greens",
                variety = "Gamthi / Country Fragrant Curry Leaves",
                minOrder = "200 Kg",
                sortOrder = order++
            ),
            ProductEntity(
                name = "Ponnanganni Keerai",
                category = ProductCategory.LEAFY_GREENS.displayName,
                description = "Nutritionally prized sessile joyweed greens. Tender shoots, freshly bundled, packed within hours of harvest.",
                packingDetails = "Chilled Air Cargo Crates with moisture control",
                isAvailable = true,
                imageType = "greens",
                variety = "Green & Pink Ponnanganni",
                minOrder = "100 Kg",
                sortOrder = order++
            ),
            ProductEntity(
                name = "Pulicha Keerai / Gongura",
                category = ProductCategory.LEAFY_GREENS.displayName,
                description = "Distinctive sour sorrel leaves widely loved in Indian cuisines. Sturdy leaves that retain tanginess throughout transit.",
                packingDetails = "Insulated shipping cartons with cooling packs",
                isAvailable = true,
                imageType = "greens",
                variety = "Red-stemmed & Green Sorrel",
                minOrder = "150 Kg",
                sortOrder = order++
            )
        )
    }
}
