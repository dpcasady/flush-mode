package flush.mode

import org.grails.config.PropertySourcesConfig
import org.grails.datastore.mapping.core.DatastoreUtils
import org.grails.orm.hibernate.connections.HibernateConnectionSourceSettings
import org.grails.orm.hibernate.connections.HibernateConnectionSourceSettingsBuilder
import org.springframework.core.env.PropertyResolver
import spock.lang.Specification

class FlushModeSpec extends Specification {

    void "flush mode is picked up from ConfigurableEnvironment property resolver"() {
        when:
        Map config = ['hibernate.flush.mode': 'always']
        HibernateConnectionSourceSettingsBuilder builder = new HibernateConnectionSourceSettingsBuilder(DatastoreUtils.createPropertyResolver(config))
        HibernateConnectionSourceSettings settings = builder.build()

        then:"The flush settings are correct"
        settings.hibernate.getFlush().mode == HibernateConnectionSourceSettings.HibernateSettings.FlushSettings.FlushMode.ALWAYS
    }

    void "flush mode is picked up from PropertySourcesConfig property resolver"() {
        when:
        Map config = ['hibernate.flush.mode': 'always']

        PropertyResolver resolver = new PropertySourcesConfig(config)
        HibernateConnectionSourceSettingsBuilder builder = new HibernateConnectionSourceSettingsBuilder(resolver)
        HibernateConnectionSourceSettings settings = builder.build()

        then:"The flush settings are correct"
        settings.hibernate.getFlush().mode == HibernateConnectionSourceSettings.HibernateSettings.FlushSettings.FlushMode.ALWAYS
    }
}