package dev.jasonhk.groovy.extensions

import org.gradle.api.Project

class ProjectExtension
{
    static boolean isProperty(final Project that, final String name)
    {
        return (that.hasProperty(name) && !(that.property(name) as String).empty)
    }
}
