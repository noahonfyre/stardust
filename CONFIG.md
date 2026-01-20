# How do I configure Stardust on my server?
On your server, locate the configuration file, located in `./config/stardust-common.toml` and open it with any text editor. (TOML support preferred)

In this file, you will find a line that says `disabled_enchantments = []`. This is where you can disable enchantments.
To disable one enchantment, you need to get the enchantment ID.
This should always be `stardust:` followed by the name of the enchantment in all lowercase, wrapped in double quotes.
If there are spaces in the enchantment name, replace them with an underscore. (`_`)
So let's say you want to remove the Tenacity enchantment from your server.
You would add to the first line `disabled_enchantments = ["stardust:tenacity"]`.
If you needed to remove a second enchantment, you would do it like this: `disabled_enchantments = ["stardust:tenacity", "stardust:soulbound"]`
