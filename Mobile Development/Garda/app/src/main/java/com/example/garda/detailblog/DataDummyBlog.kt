package com.example.garda.detailblog

import com.example.garda.R

object DataDummyBlog {
    private val nameTitle = arrayOf(
        "Holiday Garden Giving",
        "Healthy Plant Selection",
        "Learn How to Garden on a Budget"
    )

    private val nameFull = arrayOf(
        """->Arrange a community clean-up, then spend the day pulling weeds and hauling away trash. A community event instills pride and encourages people to spruce up their yards.

-> Next time you visit your local drive-through coffee stand, surprise the folks in the car behind you by paying for a cup of coffee or hot chocolate.  

-> Volunteer your time at a local animal shelter. Shelters usually need people to pet, hug, walk, and play with the animals.

-> It will soon be time to start seeds indoors. Plant a few extra seeds this year, then give the seedlings to new gardeners this spring. Patio tomatoes in containers are great gifts for apartment dwellers.

-> If you enjoy being outdoors, offer to shovel a sidewalk or driveway for an elderly neighbor.

-> Tuck a packet of vegetable or flower seeds in Christmas cards and send them to your gardening friends. If you collect seeds from your garden, put a few in homemade envelopes. Be sure to label the envelopes clearly and include planting information.
""".trimIndent(),
        """Learning the signs of a healthy plant is the first step in ensuring its overall success. Choosing healthy plants involves looking closely at all parts of the plant, beginning with the most obvious part – the leaves.

Foliage growth – A healthy plant should have plenty of healthy new growth. With the exception of plants with bi-colored or variegated leaves, most plants should display green leaves with bright, even color. Don’t buy a plant if the leaves are pale. Avoid plants with yellowing or brown leaves, or if the leaves look brown and dry along the edges.

Signs of a healthy plant include a full, bushy growth habit. Avoid long, leggy plants and, instead, choose compact, sturdy plants. Watch out for plants that look like they have been pruned; this may indicate that diseased or damaged stems have been removed to make the plant look healthier.

Roots – Healthy roots are signs of a healthy plant. Roots are difficult to see when a plant is in a pot, but you can definitely tell if the plant is rootbound. For example, pick up the plant and look at the drainage hole. If you notice roots growing through the hole, the plant has been in that pot too long. Another big sign that a plant is rootbound is roots growing on top of the potting mix.
""".trimIndent(),
        """The old saying: “You get what you pay for” is true when it comes to gardening supplies. The quality of discount and dollar store items is generally not as good as what one might expect from a greenhouse or online gardening supplier. On the other hand, if biodegradable pots from the dollar store last long enough to transplant seedlings into the garden, then they’ve served their purpose. So let’s take a look at some useful, yet cheap, garden supplies one might find at their local discount house.

Seeds – Gardeners aren’t likely to find a wide selection of vegetable and flower varieties, but they will find basic radish, carrot and marigold seeds as well as popular types of tomatoes, peppers and melons. These seed packets are usually dated for the current year so you know the seeds are fresh. 

Potting soil – Use it for potting plants, as a garden additive or for stretching out homemade compost. The quality of dollar store soil can vary, so try one bag before stocking up. 

Pots and planters – These are available in a wide assortment of sizes, colors and material. They may not be as durable as more expensive types, but are value-rich for gardeners who like the bright, clean look of new pots. 

Gardening gloves – The fabric is thinner and stitching not as strong, so discount store gloves aren’t likely to hold up for the full growing season. However, they’re great for semi-disposable uses, such as pulling poison ivy or weeding on muddy days. 

Garden decorations – From fairy garden items to solar lights, dollar store decorations are the cornerstone of frugal gardening. Generally, these items are reasonably priced so there won’t be loads of regret should they get stolen, broken or blown away in a wind storm, Another method for gardening on a dime is using non-traditional items. In the search for cheap gardening supplies, don’t limit dollar store acquisitions to the gardening department. Try some of these alternative products to meet your frugal gardening goals: Kitchen supplies, Household products, Hardware department, Toys and crafts.
""".trimIndent()
    )

    private val nameDesc = arrayOf(
        "Enjoy the weekend with lovely plants",
        "Makes your plants for growing up and strong root",
        "Only tips for buying plants around you, hopefully found available plants for you"
    )


    private var imageBlog = intArrayOf(
        R.drawable.blog1,
        R.drawable.blog2,
        R.drawable.blog3
    )

    val counterBlog: ArrayList<BlogEntity>
        get() {
            val counter = arrayListOf<BlogEntity>()
            for(position in nameTitle.indices) {
                val blogs = BlogEntity()
                blogs.nameTitle = nameTitle[position]
                blogs.nameFull = nameFull[position]
                blogs.nameDesc = nameDesc[position]
                blogs.imageBlog = imageBlog[position]

                counter.add(blogs)
            }
            return counter
        }
}